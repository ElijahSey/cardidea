package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String cardSet;

	private String topic;

	private String question;

	private String solution;

	private String hint;

	public Card() {

	}

	public Card(String cardSet, String topic, String question, String solution, String hint) {
		super();
		this.cardSet = cardSet;
		this.topic = topic;
		this.question = question;
		this.solution = solution;
		this.hint = hint;
	}

	@Override
	public String toString() {
		return "Topic: %s, Question: %s, Solution: %s".formatted(topic, question, solution);
	}

	@Override
	public boolean equals(Object o) {

		Card c = (Card) o;
		return topic.equals(c.getTopic()) && question.equals(c.getQuestion()) && solution.equals(c.getSolution());
	}

	// GETTERS AND SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardSet() {
		return cardSet;
	}

	public void setCardSet(String cardSet) {
		this.cardSet = cardSet;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
}