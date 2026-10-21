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
public class SearchDoctorController extends BaseCtl {

private Logger log = Logger.getLogger(SearchDoctorController.class.getName());
	
	@Autowired
	private SpecialityServiceInt specialityService;
	@Autowired
	private DoctorServiceInt doctorService;
	
	@ModelAttribute
	public void preload(Model model) {
		System.out.println("model "+specialityService.search(null).size());
		model.addAttribute("specialityList", specialityService.search(null));
	}
	
	
	

	@RequestMapping(value = "/home/login/user/doctor/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") DoctorForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/user/doctor/search";
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

		if (OP_PROCEED_FOR_APPOINTMENT.equals(operation)) {
			pageNo = 1;
			DoctorDTO dto = null;
			DoctorDTO doctorInfoById = null;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					System.out.println("Id is ------"+id);
					dto = new DoctorDTO();
					dto.setId(id);
					doctorInfoById = doctorService.findBypk(dto.getId());
				}
				log.info("Dto is "+dto);
				form.populate(doctorInfoById);
				model.addAttribute("form", form);
				return "fetchDoctorInfo";
				
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		DoctorDTO dto = (DoctorDTO) form.getDTO();
		
		List<DoctorDTO> list = doctorService.searchDoctorByPatient(dto, pageNo, pageSize);
		List<DoctorDTO> totallist = doctorService.searchDoctorByPatient(dto);
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
		return "searchDoctor";
	}
}
