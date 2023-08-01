import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MyMessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            Message msg = event.getMessage();
            MessageChannel mc = event.getChannel();
            String str = msg.getContentRaw();
            String order = str.substring(0, 7);
            User usr = event.getJDA().getUserById(str.substring(8));

            if (order.equals("!GetAva") && usr != null) {
                mc.sendMessage("A").queue();
                System.out.println(usr.getId() + "1");
                System.out.println(usr.getName() + "2");

                mc.sendMessage(usr.getAvatarUrl()).queue();
                System.out.println("성공");
            } else {
                System.out.println("해당 사용자를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }
}