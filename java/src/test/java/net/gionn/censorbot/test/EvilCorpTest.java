package net.gionn.censorbot.test;

import net.gionn.censorbot.CensorBot;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EvilCorpTest
{
    @Test
    public void v1()
    {
        CensorBot censorship = new CensorBot();
        censorship.setInputText( "You are a nice person" );
        censorship.addCensoredWord( "nice" );
        assertThat( censorship.getCensoredText() ).isEqualTo( "You are a XXXX person" );
    }

    @Test
    public void v2()
    {
        CensorBot censorBot = new CensorBot();
        String[] blackWords = {"nice", "pony", "sun", "light", "fun", "happy", "funny", "joy"};
        for ( String blackWord : blackWords )
        {
            censorBot.addCensoredWord( blackWord );
        }
        censorBot.setInputText( "Such a nice day with a bright sun, makes me happy" );
        assertThat( censorBot.getCensoredText() ).isEqualTo( "Such a XXXX day with a bright XXX, makes me XXXXX" );
    }

    @Test
    public void v2plus()
    {
        CensorBot censorship = new CensorBot();
        censorship.setInputText( "You are a nice person" );
        censorship.setCensorSymbol( "☂" );
        censorship.addCensoredWord( "nice" );
        assertThat( censorship.getCensoredText() ).isEqualTo( "You are a ☂☂☂☂ person" );
    }

    @Test
    public void v3()
    {
        CensorBot censorship = new CensorBot();
        censorship.setInputText( "You are so friendly!" );
        censorship.addCensoredWord( "friend" );
        assertThat( censorship.getCensoredText() ).isEqualTo( "You are so XXXXXXXXX" );
    }

    @Test
    public void v4()
    {
        CensorBot censorship = new CensorBot();
        censorship.setInputText( "Objection is bad, a better thing to do, is to agree." );
        censorship.addReplacementWord( "bad", "ungood" );
        censorship.addReplacementWord( "better", "gooder" );
        censorship.addReplacementWord( "objection", "thoughtcrime" );
        censorship.addReplacementWord( "agree", "crimestop" );
        assertThat( censorship.getCensoredText() )
                .isEqualTo( "Thoughtcrime is ungood, a gooder thing to do, is to crimestop." );
    }
}
