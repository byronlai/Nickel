package com.byronlai.nickel.ui;

import com.byronlai.nickel.logic.Game;
import com.byronlai.nickel.logic.GameResult;
import com.byronlai.nickel.logic.Outcome;
import com.byronlai.nickel.logic.Shape;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/* A graphical user interface for playing rock paper scissors lizard spock. */
public class GUI {
    private Game game;
    private JLabel statsLabel;
    private JLabel userChoiceLabel;
    private JLabel computerChoiceLabel;
    private JLabel outcomeLabel;
    private HashMap<Shape, ImageIcon> shapeIcons;
    private ImageIcon unknownIcon;
    private CardLayout cardLayout;
    private Container switcher;

    /* Launch the graphical user interface. */
    public void run() {
        game = new Game();
        loadIcons();
        createFrame();
    }

    /* Load the icons of the shapes from the resource files. */
    private void loadIcons() {
        shapeIcons = new HashMap<Shape, ImageIcon>();

        for (Shape shape : Shape.values()) {
            String name = shape.toString().toLowerCase();
            URL url = getClass().getResource("/" + name + ".png");
            shapeIcons.put(shape, new ImageIcon(url));
        }

        unknownIcon = new ImageIcon(getClass().getResource("/unknown.png"));
    }

    /* Create and display a window. */
    private JFrame createFrame() {
        JFrame frame = new JFrame("Rock Paper Scissors Lizard Spock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        initContentPane(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    /* Initialize the content pane of the window. */
    private void initContentPane(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE); 

        container.add(createVerticalSpacer());
        container.add(createStats());
        container.add(createVerticalSpacer());
        container.add(new JSeparator());
        container.add(createVerticalSpacer());
        container.add(createChoices());
        container.add(createVerticalSpacer());
        container.add(new JSeparator());
        container.add(createVerticalSpacer());

        switcher = createSwitcher();
        container.add(switcher);
        container.add(createVerticalSpacer());

        updateStats();
    }

    /* Create a vertical spacer. */
    private Component createVerticalSpacer() {
        return Box.createRigidArea(new Dimension(0, 8));
    }

    /* Create a styled label. */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 16));
        return label;
    }

    /* Create the component for displaying the statistics. */
    private Container createStats() {
        Container container = new Container();
        container.setLayout(new FlowLayout());
        statsLabel = createLabel("");
        container.add(statsLabel);
        return container;
    }

    /* Create the component for displaying the moves of the two players. */
    private Container createChoices() {
        Container container = new Container();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
        userChoiceLabel = new JLabel(unknownIcon);
        computerChoiceLabel = new JLabel(unknownIcon);
        container.add(createChoice("You", userChoiceLabel));
        container.add(createChoice("Computer", computerChoiceLabel));
        return container;
    }

    /* Create a component for displaying a player's move. */
    private Container createChoice(String title, JLabel image) {
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel text = createLabel(title);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(text);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(image);
        return container;
    }

    /* Create the component for displaying the shapes. */
    private Container createShapes() {
        Container container = new Container();
        container.setLayout(new FlowLayout());

        for (Shape shape : Shape.values())
            container.add(createShapeButton(shape));

        return container;
    }

    /* Create a button for the given shape. */
    private JButton createShapeButton(Shape shape) {
        JButton button = new JButton();
        button.setIcon(shapeIcons.get(shape));
        button.setActionCommand(shape.toString());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play(Shape.valueOf(e.getActionCommand()));
            }
        });
        return button;
    }

    /* Create the component for displaying the result. */
    private Container createResult() {
        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        outcomeLabel = createLabel("");
        outcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Play Again");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchCard("buttons");
                userChoiceLabel.setIcon(unknownIcon);
                computerChoiceLabel.setIcon(unknownIcon);
            }
        });

        container.add(outcomeLabel);
        container.add(createVerticalSpacer());
        container.add(button);
        return container;
    }

    /* Create the component for switching between the shapes and the result. */
    private Container createSwitcher() {
        Container container = new Container();
        cardLayout = new CardLayout();
        container.setLayout(cardLayout);
        container.add(createShapes(), "buttons");
        container.add(createResult(), "result");
        return container;
    }

    /* Given the player's move, play another round of RPSLV. */
    private void play(Shape shape) {
        GameResult result = game.play(shape);
        Shape computerChoice = result.getComputerChoice();
        userChoiceLabel.setIcon(shapeIcons.get(shape));
        computerChoiceLabel.setIcon(shapeIcons.get(computerChoice));
        updateStats();
        Outcome outcome = result.getOutcome();
        outcomeLabel.setText(outcome.getMessage());
        switchCard("result");
    }

    /* Update the statistics. */
    private void updateStats() {
        int wins = game.getWins();
        int losses = game.getLosses();
        int ties = game.getTies();
        String format = "Wins: %d   Losses: %d   Ties: %d";
        String stats = String.format(format, wins, losses, ties);
        statsLabel.setText(stats);
    }

    /* Switch between the shapes and the result. */
    private void switchCard(String name) {
        cardLayout.show(switcher, name);
    }
}
