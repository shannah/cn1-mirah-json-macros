/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.views;

import com.codename1.io.Log;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import java.util.HashMap;


/**
 *
 * @author shannah
 */
public class SplashScreen extends Form{
    public SplashScreen(Image frontImage, Image backImage){
        super("");
        this.setTitle("");
        
        SplashScreenTpl tpl = new SplashScreenTpl(new HashMap());
        this.setLayout(new BorderLayout());
        this.addComponent(BorderLayout.CENTER, tpl.getRoot());
        final CardView cardView = new CardView(frontImage, backImage);
        int width = Display.getInstance().getDisplayWidth();
        cardView.setPreferredH((int)((double)width * 0.6));
        cardView.setPreferredW((int)((double)width * 0.8));
        tpl.getMainContent().addComponent(cardView);
        final Runnable flipper = new Runnable(){

            public void run() {
                final Runnable flip = this;
                
                cardView.flip(new Runnable(){

                    public void run() {
                        Display.getInstance().invokeAndBlock(new Runnable(){

                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Log.e(ex);
                                }
                            }
                            
                        });
                        Display.getInstance().callSerially(flip);
                    }
                    
                });
            }
            
        };
        Display.getInstance().callSerially(flipper);
        
    }
    
    
}
