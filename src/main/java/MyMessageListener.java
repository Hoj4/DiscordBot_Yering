import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import javax.security.auth.login.LoginException;
import java.time.LocalDate;


public class MyMessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
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
                mc.sendMessage(mentionedUser.getAvatarUrl()).queue();
            }
            if (order.equals("!은비")) {
                String result = getSchedule(LocalDate.now(), 1);
                mc.sendMessage(result).queue();
            }
            if (order.equals("!은비 내일")) {
                String result = getSchedule(LocalDate.now(), 2);
                mc.sendMessage(result).queue();
            }
            if (order.equals("!은비 모레")) {
                String result = getSchedule(LocalDate.now(), 3);
                mc.sendMessage(result).queue();
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

    private String getSchedule(LocalDate event, int a) {
        LocalDate startDate = LocalDate.of(2023, 8, 5);
        int dayCount = (int) startDate.until(event).getDays();
        int remainder = dayCount % 3;

        if(a == 1) {
            if (remainder == 0) {
                return "은비는 오늘 당직입니다.";
            } else {
                return "은비는 오늘 오프입니다.";
            }
        }

        if(a == 2) {
            if ((remainder + 1) % 3 == 0) {
                return "은비는 내일 당직입니다.";
            } else {
                return "은비는 내일 오프입니다.";
            }
        }

        if(a == 3) {
            if ((remainder + 2) % 3 == 0) {
                return "은비는 모레 당직입니다.";
            } else {
                return "은비는 모레 오프입니다.";
            }
        }
        return null;
    }
}
