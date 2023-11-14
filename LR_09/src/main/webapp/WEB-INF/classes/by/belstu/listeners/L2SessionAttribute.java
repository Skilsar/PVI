package by.belstu.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class L2SessionAttribute implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Attribute added listener");
        HttpSessionAttributeListener.super.attributeAdded(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Attribute replaced listener");
        HttpSessionAttributeListener.super.attributeReplaced(event);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Attribute removed listener");
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }
}
