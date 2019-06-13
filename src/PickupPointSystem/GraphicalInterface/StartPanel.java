package PickupPointSystem.GraphicalInterface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import static java.awt.Toolkit.getDefaultToolkit;

public class StartPanel extends JPanel {

    private int height;
    private int width;
    private BackGroundPanel bgp;
    private ArrayList<Image> images = new ArrayList<>();
    private Image currentImage;
    private int currentImageNum;
    private Timer timer;

    /**
     * The constructor.
     * @param bgp
     */

    public StartPanel(BackGroundPanel bgp){
        this.bgp = bgp;
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        createImages();
        currentImage = images.get(0);
        createTimer();
        setPreferredSize(new Dimension(width*2/3, height*2/3));
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel(){
        timer.start();
        setLayout(new BorderLayout());
        add(createTouchToStartPanel(), BorderLayout.NORTH);
        add(createButtonStart(), BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    /**
     * This method adds the images to the ArrayList images.
     */

    private void createImages(){
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img0.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img1.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img2.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img3.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img4.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img5.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img6.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img7.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img8.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img9.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img10.jpg"));
        images.add(getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/starticons/img11.jpg"));
    }

    /**
     * This method creates the Timer, when the Timer expires, the background
     * image is changed to the next one.
     */

    private void createTimer(){
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentImageNum == images.size()-1){
                    currentImage = images.get(0);
                    currentImageNum = 0;
                } else {
                    currentImageNum ++;
                    currentImage = images.get(currentImageNum);
                }
                repaint();
            }
        });
    }

    /**
     * This method starts the timer and sets the first image.
     */

    public void startTimer(){
        currentImage = images.get(0);
        currentImageNum = 0;
        timer.start();
    }

    /**
     * This method creates the buttonStart, when the button is clicked,
     * the panel is changed to boxAccessPanel.
     * @return JButton
     */

    private JButton createButtonStart(){
        JButton buttonStart = new JButton();
        buttonStart.setOpaque(false);
        buttonStart.setContentAreaFilled(false);
        buttonStart.setBorderPainted(false);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("boxAccessPanel");
                timer.stop();
            }
        });
        return buttonStart;
    }

    /**
     * This method creates the panel containing the label with "Touch to start"
     * written.
     * @return JPanel.
     */

    private JPanel createTouchToStartPanel(){
        JPanel touchToStartPanel = new JPanel();
        touchToStartPanel.setBackground(Color.BLACK);
        JLabel lab = new JLabel("Touch to start");
        lab.setFont(new Font("", Font.PLAIN, 24));
        lab.setForeground(Color.WHITE);
        lab.setBackground(Color.BLACK);
        touchToStartPanel.add(lab);
        return touchToStartPanel;
    }

    /**
     * This method sets the panel background.
     * @param g
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        currentImage = currentImage.getScaledInstance(width*2/3,height*2/3,Image.SCALE_DEFAULT);
        loadImage(currentImage);
        g.drawImage(currentImage,0,0,this);
        super.paintComponent(g);
    }


    /**
     * This method ensures that the image is loaded into memory before being used,
     * blocks the execution of the code until the image is actually loaded into
     * memory.
     * @param img
     */
    private void loadImage(Image img){
        MediaTracker track = new MediaTracker(this);
        track.addImage(img,0);
        try {
            track.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}