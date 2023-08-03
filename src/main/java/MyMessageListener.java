import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class MyMessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            Message msg = event.getMessage();
            MessageChannel mc = event.getChannel();
            String str = msg.getContentRaw();
            String order = str.substring(0, 3);

            if (order.equals("!사진")) {
                List<User> mentionedUsers = event.getMessage().getMentionedUsers();
                if (mentionedUsers.isEmpty()) {
                    System.out.println("언급된 사용자가 없습니다.");
                    return;
                }

                User mentionedUser = mentionedUsers.get(0);

                System.out.println(mentionedUser.getId() + "1");
                System.out.println(mentionedUser.getName() + "2");

                mc.sendMessage(mentionedUser.getAvatarUrl()).queue();
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