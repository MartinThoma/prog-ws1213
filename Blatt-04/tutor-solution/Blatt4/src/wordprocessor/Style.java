package wordprocessor;

/**
 * Text formatting information.
 *
 * Currently this is only bold and italic.
 *
 * @author anonymer Student und Tutoren
 * @version 1.2
 */
public class Style {
    /** True if the text is formatted in bold, false otherwise. */
    private boolean bold;

    /** True if the text is formatted in italic, false otherwise. */
    private boolean italic;

    /**
     * Creates a new Style object.
     *
     * @param bold True iff the Style shall be bold.
     * @param italic True iff the Style shall be italic.
     */
    public Style(boolean bold, boolean italic) {
        this.bold = bold;
        this.italic = italic;
    }

    /**
     * Sets the bold and italic attributes according to the given style-change
     * command.
     *
     * @param change The style-change command
     */
    public void apply(StyleChange change) {
        switch (change) {
        case SET_BOLD:
            bold = true;
            break;
        case UNSET_BOLD:
            bold = false;
            break;
        case SET_ITALIC:
            italic = true;
            break;
        case UNSET_ITALIC:
            italic = false;
            break;
        }
    }

    /**
     * Returns true iff text of this Style is bold.
     *
     * @return True iff text of this Style is bold.
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Returns true iff text of this Style is italic.
     *
     * @return True iff text of this Style is italic.
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Compares Styles for equality with other objects.
     *
     * Styles are equal, when they format text in the same way, e.g. both are
     * non-bold and non-italic, both are bold but non-italic, both are non-bold
     * but italic, or both are bold and italic.
     *
     * @param other The object to compare to.
     * @return True iff the Styles are equal.
     */
    @Override
    public boolean equals(Object other) {
        // Wusstest ihr, dass Eclipse das erstellen kann?
        // Source -> Generate hashCode() and equals()
        // Man muss trotzdem noch manuell drüber schauen, aber das
        // meiste ist dann schon korrekt eingetragen.

        boolean result = false;

        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        }

        if (other instanceof Style) {
            Style otherStyle = (Style) other;
            result = bold == otherStyle.isBold()
                    && italic == otherStyle.isItalic();
        }
        return result;
    }

    /**
     * Returns a String containing HTML opening tags for the Style.
     *
     * The String must only contain &lt;b&gt;, &lt;i&gt;, where the former is
     * used for all bold Styles and the later is used for all italic Styles.
     *
     * For testability of the method's output the bold tag must come before
     * italic one.
     *
     * @return String containing HTML start tags for the Style
     */
    public String getHTMLStartTags() {
        String result = "";
        if (bold) {
            result += "<b>";
        }
        if (italic) {
            result += "<i>";
        }
        return result;
    }

    /**
     * Returns a String containing HTML end tags for the Style.
     *
     * The String contains any combination of &lt;/b&gt; for bold and &lt;/i&gt;
     * for italic text, and no other characters.
     *
     * Ordering of the tags must be consistent with getHTMLStartTags(), so that
     * an HTML document generated with these methods has correctly nested tags.
     *
     * @return String containing HTML end tags for the Style
     */
    public String getHTMLEndTags() {
        // sehr gut, du hast auf die Reihenfolge geachtet!
        String result = "";
        if (italic) {
            result += "</i>";
        }
        if (bold) {
            result += "</b>";
        }
        return result;
    }
}