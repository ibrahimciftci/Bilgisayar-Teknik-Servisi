package com.bilgeadam.BilgisayarTeknikServisi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.BilgisayarTeknikServisi.model.Proposal;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.service.ProductService;
import com.bilgeadam.BilgisayarTeknikServisi.service.ProposalService;
import com.bilgeadam.BilgisayarTeknikServisi.service.UserService;

@Controller
public class ProposalController {
	@Autowired
	ProposalService proposalService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@GetMapping(path = "/proposal")
	public ModelAndView proposal(Authentication auth) {
		ModelAndView loginView = new ModelAndView("proposal");

		SystemUser user = userService.findByUsername(auth.getName()); // zehra

		List<Proposal> proposals = proposalService.findAllByUserId(user.getId());

		loginView.addObject("proposals", proposals);
		return loginView;
	}

	@GetMapping(path = "/giveProposal")
	public ModelAndView giveProposal(Authentication auth) {
		ModelAndView loginView = new ModelAndView("giveProposal");
		Proposal proposal = new Proposal();

		loginView.addObject("proposal", proposal);
		loginView.addObject("products", productService.findAll());

		return loginView;
	}

	// (@ModelAttribute("proposal") Proposal proposal, Authentication auth)
	@PostMapping("/giveProposal")
	public String submitProposal(@ModelAttribute("proposal") Proposal proposal, Authentication auth) {
		Proposal proposalDb = new Proposal();

		proposalDb.setUser(userService.findByUsername(auth.getName()));
		proposalDb.setNote(proposal.getNote());
		proposalDb.setFiyat(proposal.getFiyat());
		proposalDb.setProduct(proposal.getProduct());

		proposalService.save(proposalDb);
		return "redirect:/proposal";
	}

	@GetMapping(path = "/proposal/delete/{id}")
	public ModelAndView proposalDelete(@PathVariable(value = "id") Long id, Authentication auth) {
		SystemUser systemUser = userService.findByUsername(auth.getName());
		Proposal proposal = proposalService.findById(id);

		SystemUser systemUserDatabase = proposal.getUser();
		Long databaseId = systemUserDatabase.getId();

		if (systemUser.getId() == databaseId) {
			proposalService.deleteById(id);
		} else {
			return new ModelAndView("redirect:/index");
		}

		return new ModelAndView("redirect:/proposal");
	}

	@GetMapping("/verifyProposal")
	public ModelAndView verifyProposal() {
		ModelAndView loginView = new ModelAndView("verifyProposal");
		List<Proposal> proposals = proposalService.findAll();

		loginView.addObject("proposals", proposals);
		return loginView;
	}

	@GetMapping(path = "/verifyProposal/accept/{id}")
	public ModelAndView proposalAccept(@PathVariable(value = "id") Long id) {
		Proposal proposal = proposalService.findById(id);
		proposal.setDurum(true);

		proposalService.save(proposal);
		return new ModelAndView("redirect:/verifyProposal");
	}

	@GetMapping(path = "/verifyProposal/deny/{id}")
	public ModelAndView proposalDeny(@PathVariable(value = "id") Long id) {
		Proposal proposal = proposalService.findById(id);
		proposal.setDurum(false);

		proposalService.save(proposal);
		return new ModelAndView("redirect:/verifyProposal");
	}

//	@PostMapping(path = "delete")
//	public ModelAndView delete(Long id) {
//		proposalService.deleteById(id);
//		return new ModelAndView("redirect:/proposal");
//	}
}