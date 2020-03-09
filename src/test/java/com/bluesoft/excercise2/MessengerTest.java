package com.bluesoft.excercise2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class MessengerTest {

    //@Disabled("Not implemented yet")
    @Test
    public void shouldSendAMessage() {
        //given
        TemplateEngine templateEngine = mock(TemplateEngine.class);
        MailServer mailServer = mock(MailServer.class);
        Client client = mock(Client.class);
        Template template = mock(Template.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);

        //when
        messenger.sendMessage(client, template);

        //then
        verify(templateEngine).prepareMessageBody(template, client);
        verify(mailServer).send(client.getEmailAddress(), templateEngine.prepareMessageBody(template, client));
    }
}
