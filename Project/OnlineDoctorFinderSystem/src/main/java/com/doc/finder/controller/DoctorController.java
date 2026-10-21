package com.doc.finder.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.doc.finder.dto.DoctorDTO;
import com.doc.finder.exception.DuplicateRecordException;
import com.doc.finder.form.DoctorForm;
import com.doc.finder.service.DoctorServiceInt;
import com.doc.finder.service.SpecialityServiceInt;

@Controller
public class DoctorController extends BaseCtl {

private Logger log = Logger.getLogger(DoctorController.class.getName());
	
	@Autowired
	private SpecialityServiceInt specialityService;
	@Autowired
	private DoctorServiceInt doctorService;
	
	@ModelAttribute
	public void preload(Model model) {
		System.out.println("model "+specialityService.search(null).size());
		model.addAttribute("specialityList", specialityService.search(null));
	}
	
	
	@GetMapping("/home/login/doctor")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") DoctorForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			DoctorDTO bean = doctorService.findBypk(id);
			form.populate(bean);
		}
		return "doctor";
	}

	@PostMapping("/home/login/doctor")
	public String submit(@Valid @ModelAttribute("form") DoctorForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/doctor";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "doctor";
				}
				DoctorDTO bean = (DoctorDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					bean.setSpeciality(specialityService.findBypk(form.getSpecialityId()));
					bean.setSpecialityId(form.getSpecialityId());
					doctorService.update(bean);
					model.addAttribute("success", "Doctor update Successfully!!!!");
				} else {
					bean.setSpeciality(specialityService.findBypk(form.getSpecialityId()));
					bean.setSpecialityId(form.getSpecialityId());
					doctorService.add(bean);
					model.addAttribute("success", "Doctor Added Successfully!!!!");
				}
				return "doctor";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "doctor";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/doctor/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") DoctorForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/doctor/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					DoctorDTO dto = new DoctorDTO();
					dto.setId(id);
					doctorService.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		DoctorDTO dto = (DoctorDTO) form.getDTO();
		
		List<DoctorDTO> list = doctorService.search(dto, pageNo, pageSize);
		List<DoctorDTO> totallist = doctorService.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "doctorList";
	}
}
