package com.kayo.helpdesk.service;

import com.kayo.helpdesk.dto.ticket.TicketCreateRequestDTO;
import com.kayo.helpdesk.dto.ticket.TicketResponseDTO;
import com.kayo.helpdesk.entity.enums.Role;
import com.kayo.helpdesk.entity.enums.TicketStatus;
import com.kayo.helpdesk.entity.ticket.Ticket;
import com.kayo.helpdesk.entity.user.User;
import com.kayo.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket create(TicketCreateRequestDTO dto, User user) {
        Ticket ticket = new Ticket();
        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setUser(user);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<TicketResponseDTO> list(User user) {
        List<Ticket> tickets =
         user.getRole() == Role.USER
                ? ticketRepository.findByUserId(user.getId())
                 :ticketRepository.findAll();
        return tickets.stream()
                .map(this::toResponse)
                .toList();
    }

    private TicketResponseDTO toResponse(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus().name(),
                ticket.getUser().getUsername(),
                ticket.getTech() != null ? ticket.getTech().getUsername() : null
        );
    }
}
