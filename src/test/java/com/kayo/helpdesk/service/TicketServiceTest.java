package com.kayo.helpdesk.service;

import com.kayo.helpdesk.entity.enums.Role;
import com.kayo.helpdesk.entity.enums.TicketStatus;
import com.kayo.helpdesk.entity.ticket.Ticket;
import com.kayo.helpdesk.entity.user.User;
import com.kayo.helpdesk.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void shouldCreateTicketWhitOpenStatus(){
        User user = new User();
        user.setId(1L);
        user.setRole(Role.USER);

        when(ticketRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Ticket ticket = ticketService.create(
                "Erro no sistema",
                "Sistemas fora do ar",
                user);

        assertEquals(TicketStatus.OPEN, ticket.getStatus());
        assertEquals(user, ticket.getUser());
    }
}
