package net.gionn.censorbot;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CensorBot
{
    private String inputText;
    private Map<String, String> blacklist = new HashMap<>();
    private Character censorSymbol = 'X';

    public String getCensoredText()
    {
        String[] words = this.inputText.split( " " );

        for ( int i = 0; i < words.length; i++ )
        {
            String currentWord = words[i];
            currentWord = censorWord( currentWord );
            words[i] = currentWord;
        }

        return StringUtils.join( words, " " );
    }

    private String censorWord( String word )
    {
        for ( Map.Entry<String, String> blacklistEntry : blacklist.entrySet() )
        {
            String blacklistedWord = blacklistEntry.getKey();
            if ( word.contains( blacklistedWord ) )
            {
                String replacement = blacklistEntry.getValue();
                String obfuscated = obfuscate( blacklistedWord, replacement );
                word = word.replace( blacklistedWord, obfuscated );
                word = finishObfuscation( word );
            }
        }
        return word;
    }

    private String finishObfuscation( String word )
    {
        char[] chars = word.toCharArray();
        for ( int i = 1; i < chars.length; i++ )
        {
            if ( chars[i - 1] == censorSymbol && ( Character.isLetter( chars[i] ) || chars[i] == '!') )
            {
                chars[i] = censorSymbol;
            }
        }
        return String.valueOf( chars );
    }

    /**
     * @param word        the string to obfuscate
     * @param replacement the string used for obfuscation
     * @return an obfuscated string
     */
    private String obfuscate( String word, String replacement )
    {
        if ( replacement.length() == 1 )
        {
            return StringUtils.repeat( replacement, word.length() );
        }
        else
        {
            return replacement;
        }
    }

    private String toUppercaseFirst( String alignedString )
    {
        char[] chars = alignedString.toCharArray();
        chars[0] = String.valueOf( chars[0] ).toUpperCase().charAt( 0 );
        alignedString = String.valueOf( chars );
        return alignedString;
    }

    private boolean isFirstUppercase( String referenceString )
    {
        return Character.isUpperCase( referenceString.charAt( 0 ) );
    }

    //region getter setter
    public void addCensoredWord( String censored )
    {
        blacklist.put( censored, censorSymbol.toString() );
    }

    public void addReplacementWord( String censored, String replacement )
    {
        blacklist.put( censored, replacement );
        if ( !isFirstUppercase( censored ) )
        {
            blacklist.put( toUppercaseFirst( censored ), toUppercaseFirst( replacement ) );
        }
    }

    public void setInputText( String inputText )
    {
        this.inputText = inputText;
    }

    public void setCensorSymbol( String symbol )
    {
        this.censorSymbol = symbol.charAt( 0 );
    }
    //endregion
}
