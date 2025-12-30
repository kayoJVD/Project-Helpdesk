package com.kayo.helpdesk.service;

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

    public Ticket create(String title, String description, User user) {
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setUser(user);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> list(User user) {
        if (user.getRole() == Role.USER){
            return ticketRepository.findByUserId(user.getId());
        }
        return ticketRepository.findAll();
    }
}
