package com.partymanager.finalproject.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.CoinModifier;
import com.partymanager.finalproject.dto.Note;
import com.partymanager.finalproject.dto.OnePartyPlayer;
import com.partymanager.finalproject.dto.XpModifier;
import com.partymanager.finalproject.service.OnePartyPlayerService;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.PlayerCharacterService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class PartyController {

	@Autowired
	private PartyService partyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerCharacterService pcService;
	@Autowired
	private OnePartyPlayerService oPPservice;

	@GetMapping("/join-party/{partyId}")
	public String getPartyPage(ModelMap model, @PathVariable Long partyId) {

		Party party = partyService.findByPartyId(partyId).orElseThrow();
		String partyName = party.getPartyName();

		// create One party Players for Thymeleaf
		List<OnePartyPlayer> onePartyPlayers = oPPservice.createOnePartyPlayers(partyId);
		Note newNote = new Note();
		if (party.getNote() == null) {
			party.setNote(newNote);
		}
		//create or fetch note to add to page
		Note note = party.getNote();
		model.addAttribute("note", note);

		//The Dm will be the only one in the party with Role_DM
		User dm = oPPservice.getDm(party);
		Long dmId = dm.getUserId();
		String dmName = dm.getFirstName();
		model.put("dm", dm);
		model.put("dmFirstName", dmName);

		// Get everyone but the Dm
		List<OnePartyPlayer> justPlayers = onePartyPlayers.stream().filter(u -> u.getOnePartyUserId() != (dmId))
				.collect(Collectors.toList());

		// get the logged in user and see if they're already in the party
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());
		String userFirstName = user.getFirstName();
		model.put("userFirstName", userFirstName);

		Boolean characterBoolean = false;
		Boolean characterInParty = pcService.checkIfInParty(partyId, user.getUserId());
		// determine if player is in party
		Boolean inParty = partyService.isInParty(partyName, user);

		// put data objects for experience and currency transactions
		XpModifier xpModifier = new XpModifier();
		CoinModifier coinModifier = new CoinModifier();
		// check if party fund has been created
		Boolean partyFunded = oPPservice.checkForPartyFund(partyId);

		List<PlayerCharacter> userPcs = user.getCharacters();
		List<String> pcNames = new ArrayList<>();
		for (PlayerCharacter pc : userPcs) {
			String pcName = pc.getName();
			pcNames.add(pcName);
		}
		List<PlayerCharacter> userPcInParty = new ArrayList<>();
		for (PlayerCharacter pc : userPcs) {
			Party pcParty = pc.getParty();
			if (pcParty.equals(party)) {
				userPcInParty.add(pc);
			}
		}
		try {
			PlayerCharacter pcInParty = userPcInParty.get(0);
			String pcInPartyName = pcInParty.getName();
			List<String> oPPpcNames = new ArrayList<>();
			for (OnePartyPlayer player : justPlayers) {
				String onePartyPcName = player.getCharacterName();
				oPPpcNames.add(onePartyPcName);
			}
			for (String oPPpcName : oPPpcNames) {
				if (oPPpcName.equalsIgnoreCase(pcInPartyName))
					characterBoolean = true;
				model.put("pcInPartyName", pcInPartyName);
			}
		} catch (Exception e) {
			characterBoolean = false;
		}

		// Boolean objects
		model.put("inParty", inParty);
		model.put("partyFund", partyFunded);
		model.put("characterInParty", characterInParty);
		model.put("characterBoolean", characterBoolean);
		// users minus DM
		model.put("players", justPlayers);
		// The Party
		model.put("party", party);

		// DTO objects
		model.put("xpModifier", xpModifier);
		model.put("goldModifier", coinModifier);
		model.put("silverModifier", coinModifier);
		model.put("copperModifier", coinModifier);

		return "party";
	}

	@PostMapping("/join-party/{partyId}")
	public String joinParty(ModelMap model, @PathVariable Long partyId) {
		Party foundParty = partyService.findByPartyId(partyId).orElseThrow();
		model.put("partyToBeJoined", foundParty);
		model.put("party", foundParty);
		User user = userService.findLoggedIn();
		Long userId = user.getUserId();
		partyService.joinPartyById(partyId, userId);
		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/remove-from-party/{partyId}/{firstName}")
	public String removeFromParty(@PathVariable String firstName, @PathVariable Long partyId) {
		if (firstName.equals("Party Fund")) {
			User partyFund = partyService.getPartyFund(partyId);
			Long partyFundUId = partyFund.getUserId();
			partyService.removeFromParty(partyFundUId, partyId);
		} else {
			User user = userService.findByFirstName(firstName);
			Long userId = user.getUserId();
			partyService.removeFromParty(userId, partyId);
		}

		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/leave-party/{partyId}/")
	public String leaveParty(@PathVariable Long partyId) {
		User user = userService.findLoggedIn();
		Long userId = user.getUserId();
		partyService.leaveParty(partyId, userId);
		return "redirect:/home";
	}

	@PostMapping("/create-party-fund/{partyId}")
	public String createPartyFund(@PathVariable Long partyId) {
		oPPservice.createPartyFund(partyId);
		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/create-nonUser-player/{partyId}")
	public String createNonUserPlayer(@PathVariable Long partyId,
			@ModelAttribute("onePartyPlayer") OnePartyPlayer onePartyPlayer,
			String firstName,  String characterName) {
	//	Party party = partyService.findByPartyId(partyId).orElseThrow();
		
		
////		model.addAttribute("onePartyPlayerFirstName", onePartyPlayer.getFirstName());
////		String firstName = onePartyPlayer.getFirstName();
////		model.addAttribute("onePartyPlayerCharacterName", onePartyPlayer.getCharacterName());
////		String characterName = onePartyPlayer.getCharacterName();
//		
//		User newUser = new User();
//	
//		byte[] array = new byte[7]; // length is bounded by 7
//		new Random().nextBytes(array);
//		String generatedUserName = new String(array, Charset.forName("UTF-8"));
//		
//		new Random().nextBytes(array);
//		String generatedPassword = new String(array, Charset.forName("UTF-8"));
//		
//		//fake register a new user
//		newUser.setFirstName(firstName);
//		newUser.setLastName("lastName");
//		newUser.setPassword(generatedPassword);
//		newUser.setUsername(generatedUserName);
//		Set<Authorities> auths = new HashSet<>();
//		Authorities auth = new Authorities();
//		auth.setAuthority("ROLE_USER");
//		auths.add(auth);
//		newUser.setAuthorities(auths);
//		userService.save(newUser);
//		
//		//create empty list of parties to add this party to users party
//		List<Party> parties = new ArrayList<>();
//		parties.add(party);
//		newUser.setParties(parties);
//		//Empty list of player characters to add this users characters
//		List<PlayerCharacter> pcs = new ArrayList<>(); 
//		
//		PlayerCharacter pc = new PlayerCharacter();
//		pc.setName(characterName);
//		pc.setXp(0);
//		pc.setAlignment("");
//		pc.setUser(newUser);
//		pc.setParty(party);
//		pc.setCopper(0);
//		pc.setSilver(0);
//		pc.setGold(0);
//		pc.setLevel(1);
//		pc.setXpToLevel(300);
//		pcService.save(pc);
//		pcs.add(pc);
//		newUser.setCharacters(pcs);
//		userService.save(newUser);
		User newUser = oPPservice.createNonUserPlayer(partyId, firstName, characterName);
		
		System.out.println(newUser);

		return "redirect:/join-party/{partyId}";
	}
	
	@PostMapping("/add-note/{partyId}")
	public String updateNote(@PathVariable Long partyId, @ModelAttribute("note") Note note, 
			BindingResult result, ModelMap model) {
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		if(party.getNote() == null) {
			Note newNote = new Note();
			party.setNote(newNote);
			partyService.save(party);
		}
		Note partyNote = party.getNote();
		model.addAttribute("text", note.getText());
		String textToAdd = note.getText();
		System.out.println(textToAdd);
		partyNote.setText(textToAdd);
		partyService.save(party);
		System.out.println(partyNote.toString());
		return "redirect:/join-party/{partyId}";
	}
	
}
