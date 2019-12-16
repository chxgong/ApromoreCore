package org.apromore.service.csvimporter;

import java.util.List;
import java.util.Map;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

/**
 * A sample of a CSV log.
 */
public interface LogSample {

    // Constants (TODO: remove these, as they are UI-related)

    final String popupID = "pop_";
    final String textboxID = "txt_";
    final String labelID = "lbl_";


    // Accessors

    List<String> getHeader();

    List<List<String>> getLines();

    Map<String, Integer> getHeads();

    String getTimestampFormat();

    void setTimestampFormat(String s);

    String getStartTsFormat();

    void setStartTsFormat(String s);

    List<Listbox> getLists();

    List<Integer> getIgnoredPos();

    Map<Integer, String> getOtherTimeStampsPos();

    Div getPopUPBox();

    void setPopUPBox(Div popUPBox);


    // Public methods

    /**
     * Based on the sampled CSV lines, try to set the <var>timestampFormat</var> and <var>startTsFormat</var> properties.
     *
     * @return a non-null list of nonfatal error messages; an empty list indicates error-free executation
     */
    List<String> automaticFormat();

    void openPopUp(LogSample sample);
    void setIgnoreAll(Window window, LogSample sample);
    void setOtherAll(Window window, LogSample sample);
    void setOtherTimestamps(LogSample sample);
    void tryParsing(String format, int colPos, LogSample sample);
}
