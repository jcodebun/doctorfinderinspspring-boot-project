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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.doc.finder.dto.AppointmentDTO;
import com.doc.finder.dto.DoctorDTO;
import com.doc.finder.dto.UserDTO;
import com.doc.finder.exception.DuplicateRecordException;
import com.doc.finder.form.AppointmentForm;
import com.doc.finder.form.DoctorForm;
import com.doc.finder.service.AppointmentServiceInt;
import com.doc.finder.service.DoctorServiceInt;
import com.doc.finder.service.SpecialityServiceInt;
import com.doc.finder.service.UserServiceInt;
import com.doc.finder.util.DataUtility;

@Controller
public class AppointmentController extends BaseCtl {

private Logger log = Logger.getLogger(AppointmentController.class.getName());
	
	@Autowired
	private SpecialityServiceInt specialityService;
	@Autowired
	private DoctorServiceInt doctorService;
	@Autowired
	private AppointmentServiceInt appointmentService;
	@Autowired
	private UserServiceInt userService;
	
	@ModelAttribute
	public void preload(Model model) {
		System.out.println("model "+specialityService.search(null).size());
		model.addAttribute("specialityList", specialityService.search(null));
	}
	
	@PostMapping("/home/login/user/appointment")
	public String submit(@ModelAttribute("form") DoctorForm form,@ModelAttribute("aform") AppointmentForm aform, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {
		UserDTO user  = (UserDTO)session.getAttribute("user");
		
		try {
			if (OP_TAKE_APPOINTMENT.equalsIgnoreCase(form.getOperation())) {

					AppointmentDTO bean = new AppointmentDTO();
					bean.setDateOfAppointment(request.getParameter("date"));
					bean.setSlot(request.getParameter("timeslot"));
					bean.setDoctorId(form.getId());
					bean.setDoctor(doctorService.findBypk(form.getId()));
					bean.setUserId(user.getId());
					bean.setUser(userService.findBypk(user.getId()));
					bean.setCreatedDatetime(DataUtility.getCurrentTimestamp());
					bean.setStatus("PENDING");
					appointmentService.add(bean);
					
					model.addAttribute("appBean", bean);
					model.addAttribute("success", "Appointment Added Successfully!!!!");
					
				return "confirmAppointment";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "confirmAppointment";
		}
		return "";
	
	}
	
	@RequestMapping(value = "/home/login/appointment/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) throws DuplicateRecordException {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/appointment/search";
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

		if (OP_REJECT.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					dto.setStatus("REJECTED");
					appointmentService.rejectAppointment(dto);
				}
				model.addAttribute("success", "Appointment Rejected Successfully!!!");
			} else {
				model.addAttribute("error", "Select a record");
			}
		}
		if (OP_APPROVE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					dto.setStatus("ACCEPTED");
					appointmentService.update(dto);
				}
				model.addAttribute("success", "Appointment Approved Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AppointmentDTO dto = (AppointmentDTO) form.getDTO();
		
		List<AppointmentDTO> list = appointmentService.search(dto, pageNo, pageSize);
		List<AppointmentDTO> totallist = appointmentService.search(dto);
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
		return "appointmentList";
	}
}
