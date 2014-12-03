/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.weblite.oscon2014.schedulebuilder.views;

import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Transform;
import com.codename1.ui.animations.Animation;
import com.codename1.ui.animations.Motion;
import com.codename1.util.MathUtil;

/**
 *
 * @author shannah
 */
public class CardView extends Component {
    private Image front;
    private Image back;
    // 0 is front, 1.0 is back
    private float flipState=0f;
    
    public CardView(Image front, Image back){
        this.front = front;
        this.back = back;
        
    }

    
    public void setFlipState(float d){
        flipState = d;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
        
        if ( g.isPerspectiveTransformSupported()){
            float displayH = Display.getInstance().getDisplayHeight();
            float displayW = Display.getInstance().getDisplayWidth();
            double midX = (float)x+(float)w/2.0;
            double midY = (float)y+(float)h/2.0;
            float zNear = 1600;
            float zFar = zNear+3000;
            double fovy = 0.25;
           
            
            Transform perspectiveT = Transform.makePerspective((float)fovy, (float)displayW/(float)displayH, zNear, zFar);
            float[] bottomRight = perspectiveT.transformPoint(new float[]{displayW, displayH, zNear});
            
            Transform t = Transform.makeTranslation(0,0, 0);
            
            
            float xfactor = -displayW/bottomRight[0];
            float yfactor = -displayH/bottomRight[1];
            
            
            t.scale(xfactor,yfactor,0f);
            t.translate((x+w/2)/xfactor, (y+h/2)/yfactor, 0);
            
            
            t.concatenate(perspectiveT);
            float cameraZ = -zNear-w/2;
            float cameraX = -x-w/2;
            float cameraY = -y-h/2;
            t.translate(cameraX, cameraY, cameraZ);
            
            
            t.translate((float)midX, y, 0);
            
            Image img = null;
            if ( flipState < 0.5 ){
                img = front;
                // We are showing the front image
                // We will rotate it up to 90 degrees
                // 0 -> 0 degrees
                // 0.5 -> 90 degress
                double sin = flipState * 2.0;
                double angle = MathUtil.asin(sin);
                t.rotate((float)angle, 0, 1, 0);// rotate about y axis
            } else {
                img = back;
                // We are showing the back image
                // We are showing the back of the image
                //  We will rotate it from 90 degrees back to 0
                // 0.5 -> 90 degrees
                // 1.0 -> 0 degrees
                double sin = (1.0-flipState)*2.0;
                double angle = Math.PI-MathUtil.asin(sin);
                t.rotate((float)angle, 0, 1, 0);// rotate about y axis
            }
            t.translate(-(float)midX, -y, 0);
            Transform oldTransform = g.getTransform();
            g.setTransform(t);
            g.drawImage(img, x, y, w, h);
            g.setTransform(oldTransform);
            
            
        } else {
            if ( flipState < 0.5 ){
                int frontX = x+(int)(flipState*(float)w);
                int frontWidth = (int)((float)w * (1.0-flipState*2.0));
                g.drawImage(front, frontX, y, frontWidth, h);
            } else {
                double backState = 1.0-flipState;
                int backX = x+(int)(backState*(float)w);
                int backWidth = (int)((float)w * (1.0-backState*2.0));
                g.drawImage(back, backX, y, backWidth, h);
            }
        }
        
        
    }
    
    
    public void flip(){
        FlipAnimation anim = new FlipAnimation();
        this.getComponentForm().registerAnimated(anim);
    }
    
    public void flip(Runnable onFinish){
        FlipAnimation anim = new FlipAnimation();
        anim.onFinish = onFinish;
        this.getComponentForm().registerAnimated(anim);
    }
    
    
    class FlipAnimation implements Animation {
        
        Motion motion;
        boolean firstFinished = false;
        boolean started = false;
        Runnable onFinish;
        
        
        FlipAnimation(){
            int start = Math.abs(flipState-0)<0.0001 ? 0 : 180;
            int end = start == 0 ? 180 : 0;
            motion = Motion.createLinearMotion(start, end, 1000);
            
        }
        

        public boolean animate() {
            if ( !started ){
                started = true;
                motion.start();
                return true;
            }
            if ( motion == null ){
                return false;
            }
            
            int val = motion.getValue();
            double valInRadians = Math.PI/180f*(double)val;
            double projectedPos = Math.cos(valInRadians);
            // 1 -> 0
            // -1 -> 1
            flipState = (float)((-projectedPos)/2.0+0.5);
            
            if (motion.isFinished() ){
                if ( !firstFinished ){
                    firstFinished = true;
                    return true;
                } else {
                    destroy();
                    if ( onFinish != null ){
                        Display.getInstance().callSerially(onFinish);
                    }
                    return false;
                }
            }
            
            
            return true;
        }
        
        public void destroy(){
            getComponentForm().deregisterAnimated(this);
            //getComponentForm().revalidate();
        }

        public void paint(Graphics g) {
            CardView.this.repaint();
        }

        
        
        
    }
    
    
    
    
}