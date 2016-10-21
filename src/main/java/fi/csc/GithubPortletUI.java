package fi.csc;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


@SuppressWarnings("serial")
@Widgetset("fi.csc.AppWidgetSet")
public class GithubPortletUI extends UI {

    private static Log log = LogFactoryUtil.getLog(GithubPortletUI.class);

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        String https_url = "https://raw.githubusercontent.com/avaa-csc/avaa-paituli/master/paituli-plugin/webcontent/rajapinta.html";
        URL url;
        String content = "Sisältöä ei löytynyt";
        try {
	        url = new URL(https_url);
	        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            StringBuilder sb = new StringBuilder();
            while ((input = br.readLine()) != null){
                sb.append(input);
                sb.append("\n");
            }
           content = sb.toString();
        } catch (MalformedURLException e) {
            content = "MalformedURLException:"+https_url;
	        e.printStackTrace();
        } catch (IOException e) {
            content = e.getMessage();
	        e.printStackTrace();
        }
        final Label label = new Label(content, ContentMode.HTML);
        layout.addComponent(label);
    }


}
