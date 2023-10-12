package presentation.popup;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entity.Card;
import entity.CardSet;
import logic.parsers.CardParser;
import presentation.basic.ContentPanel;
import presentation.basic.PopupFrame;
import presentation.util.VisualException;

public class CardExport extends PopupFrame {

	private JList<CardParser> parsers;
	private CardSet cardSet;

	public CardExport(ContentPanel mainPanel, CardSet cardSet) {
		super(mainPanel, true);
		this.cardSet = cardSet;
	}

	@Override
	protected JPanel createContent() {
		JPanel panel = new JPanel(new BorderLayout(15, 15));

		JPanel center = new JPanel(new GridBagLayout());
		parsers = gui.createList(dp.getParsers(), new CardParser[0]);
		parsers.setFixedCellWidth(150);
		JScrollPane scroll = gui.createScrollPane(parsers);
		center.add(scroll);

		JPanel buttonPanel = new JPanel();
		JButton export = gui.createButton(i18n.getString("export"));
		export.addActionListener(e -> export());
		buttonPanel.add(export);

		panel.add(center, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}

	private void export() {

		if (parsers.isSelectionEmpty()) {
			parsers.setSelectedIndex(0);
		}
		CardParser parser = parsers.getSelectedValue();

		JFileChooser explorer = gui.createFileChooser();
		explorer.setFileFilter(parser.getFileFilter());
		explorer.setSelectedFile(new File(cardSet.getName() + parser.getExtension()));
		super.close();
		int status = explorer.showSaveDialog(frame);
		if (status != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = explorer.getSelectedFile();

		Map<String, List<Card>> cards = new HashMap<>();
		for (Card card : dp.getCardsOfSet(cardSet)) {
			String name = card.getTopic().getName();
			if (!cards.containsKey(name)) {
				cards.put(name, new ArrayList<>());
			}
			cards.get(name).add(card);
		}
		try {
			parser.write(file, cardSet, cards);
		} catch (IOException e) {
			throw new VisualException(mainPanel, e);
		}
	}
}