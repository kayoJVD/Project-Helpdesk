package com.kayo.helpdesk.repository;

import com.kayo.helpdesk.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUserId(Long userId);
}
