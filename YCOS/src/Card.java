public class Card {

	int suit, rank;
	String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
	String[] ranks = { "empty", "Ace", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "Jack", "Queen", "King" };

	//��Ӧ����
	String[] ��ɫ = { "÷��", "����", "����", "����" };
	String[] ���� = { "��", "A", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "J", "Q", "K" };
	/**
	 * �չ��췽��
	 */
	public Card() {
		this.suit = 0;
		this.rank = 0;
	}

	/**
	 * @param suit
	 * @param rank
	 */
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public void print() {
		System.out.println(ranks[rank] + " of " + suits[suit]+"������ "+��ɫ[suit]+"@"+����[rank]);
	}

	public int compareTo(Card c) {
		if (this.rank == 1 || c.rank == 1) {// ��Ace�����
			if (this.rank == 1 && c.rank == 1)
				return 0;
			if (this.rank == 1)
				return 1;
			return -1;
		} else {// �������ж�������Ace
			if (this.rank > c.rank)
				return 1;
			if (this.rank < c.rank)
				return -1;
			return 0;
		}
	}
}