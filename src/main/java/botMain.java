import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class botMain extends ListenerAdapter
{
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("MTEzNTk1MTQzNDI0NDk1MjE5Ng.G_sUIo.oQAlqK5oYvIrmxV0BgC7s1a6un9F4nQ4xCX3LA").build();
                //.enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()

        //You can also add event listeners to the already built JDA instance
        // Note that some events may not be received if the listener is added after calling build()
        // This includes events such as the ReadyEvent
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.addEventListener(new MyMessageListener());
    }
}
