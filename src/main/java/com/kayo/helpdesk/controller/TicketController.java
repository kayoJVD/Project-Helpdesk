package com.kayo.helpdesk.controller;

import com.kayo.helpdesk.dto.ticket.TicketCreateRequestDTO;
import com.kayo.helpdesk.dto.ticket.TicketResponseDTO;
import com.kayo.helpdesk.entity.enums.Role;
import com.kayo.helpdesk.entity.ticket.Ticket;
import com.kayo.helpdesk.entity.user.User;
import com.kayo.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> create(@RequestBody @Valid TicketCreateRequestDTO dto){
        User fakeUser = new User();
        fakeUser.setId(1L);
        fakeUser.setUsername("Kayo");
        fakeUser.setRole(Role.USER);

        Ticket ticket = ticketService.create(dto, fakeUser);

        TicketResponseDTO response = new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus().name(),
                fakeUser.getUsername(),
                null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> list(){
        User fakeUser = new User();
        fakeUser.setId(1L);
        fakeUser.setRole(Role.USER);

        return ResponseEntity.ok(ticketService.list(fakeUser));
    }
}
