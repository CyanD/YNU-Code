/**
 * һ���˿���
 */

/**
 * @author HYC
 * 
 */
public class Deck {
	Card[] cards;

	/**
	 * �չ��췽��������52���˿���
	 */
	public Deck() {
		this.cards = new Card[52];
		int index = 0;
		while (index < 52) {
			for (int suit = 0; suit <= 3; suit++) {
				for (int rank = 1; rank <= 13; rank++) {
					this.cards[index] = new Card(suit, rank);
					index++;
				}
			}
		}
	}

	public Deck(int n) {
		this.cards = new Card[n];
	}

	public void print() {
		for (int i = 0; i < cards.length; i++) {
			cards[i].print();
		}
	}

	public int length() {
		return cards.length;
	}

	// Ϊ�����㣬��ʱ�Ȳ�����235�������
	// ����˳��single< pair< straight< flush< straight flush< boss
	public int compareSubDeckTo(Deck subDeck) {
		int mark;
		mark = encodeToNumb(this) - encodeToNumb(subDeck);
		if (mark > 0) {
			return 1;
		}
		if (mark < 0) {
			return -1;
		}
		// Ϊͬ������ʱ
		this.sort();
		subDeck.sort();
		switch (encodeToNumb(this)) {
		case 1:// ��Ϊ����
		case 3:// ���߶�Ϊ˳��
		case 4:// ���߶���ͬ��
		case 5:// ���߶���ͬ��˳
			if (this.cards[2].rank != 1 && subDeck.cards[2].rank != 1)// ����Ace
			{
				if (this.cards[2].rank > subDeck.cards[2].rank)
					return 1;
				if (this.cards[2].rank < subDeck.cards[2].rank)
					return -1;
				// �������ͬ������Ƚϴδ���
				if (this.cards[1].rank > subDeck.cards[1].rank)
					return 1;
				if (this.cards[1].rank < subDeck.cards[1].rank)
					return -1;
				// �δ���Ҳ��ͬʱ����Ƚ���С��
				if (this.cards[0].rank > subDeck.cards[0].rank)
					return 1;
				if (this.cards[0].rank < subDeck.cards[0].rank)
					return -1;
				return 0;// ȫ���Ƶ�����ͬ������Ϊһ����
			} else // ����Ace
			{
				if (this.cards[2].rank == 1 && subDeck.cards[2].rank != 1)
					return 1;
				if (this.cards[2].rank != 1 && subDeck.cards[2].rank == 1)
					return -1;
				if (this.cards[2].rank == 1 && subDeck.cards[2].rank == 1) {
					if (this.cards[1].rank == 1 && subDeck.cards[1].rank != 1)
						return 1;
					if (this.cards[1].rank != 1 && subDeck.cards[1].rank == 1)
						return -1;
					if (this.cards[1].rank == 1 && subDeck.cards[1].rank == 1)
					{
						if (this.cards[0].rank == 1 && subDeck.cards[0].rank != 1)
							return 1;
						if (this.cards[0].rank != 1 && subDeck.cards[0].rank == 1)
							return -1;
						
					}
				}
				return -404;
			}
		case 2:// ��Ϊ����
			Card[] c1 = findPairCardandSingleInPairs(this);
			Card[] c2 = findPairCardandSingleInPairs(subDeck);
			if (c1[0].rank > c2[0].rank) {
				return 1;
			}
			if (c1[0].rank < c2[0].rank) {
				return -1;
			}
			if (c1[1].rank > c2[1].rank) {
				return 1;
			}
			if (c1[1].rank < c2[1].rank) {
				return -1;
			}
			return 0;
		case 6:// ����ը��
			if (this.cards[0].rank > subDeck.cards[0].rank)
				return 1;
			if (this.cards[0].rank < subDeck.cards[0].rank)
				return -1;
			return 0;
		}
		return -404;// ������Ϣ
	}

	// һ����d�ǶԼ�[0]Ȼ���һ��ʲô[1]
	private static Card[] findPairCardandSingleInPairs(Deck d) {
		Card[] c = new Card[3];
		c[0] = d.cards[0];
		c[1] = d.cards[1];
		c[2] = d.cards[2];
		Card[] result = new Card[1];
		if (c[0] == c[1]) {
			result[0] = c[0];
			result[1] = c[2];
		}
		if (c[0] == c[2]) {
			result[0] = c[0];
			result[1] = c[1];
		}
		if (c[1] == c[2]) {
			result[0] = c[1];
			result[1] = c[0];
		}
		return result;

	}

	private static int encodeToNumb(Deck cards) {
		if (cards.isSingle())
			return 1;
		if (cards.isPair())
			return 2;
		if (cards.isStraight())
			return 3;
		if (cards.isFlush())
			return 4;
		if (cards.isStraighFlush())
			return 5;
		if (cards.isBoss())
			return 6;
		return -1;
	}

	// �ж��Ƿ�Ϊ����
	public boolean isSingle() {
		if (!this.isBoss() && !this.isFlush() && !this.isPair()
				&& !this.isStraighFlush() && !this.isStraight())
			return true;
		else
			return false;
	}

	// �ж��Ƿ�Ϊ����
	public boolean isPair() {
		int edge = 0;
		if (this.cards[0].compareTo(this.cards[1]) == 0)
			edge++;
		if (this.cards[0].compareTo(this.cards[2]) == 0)
			edge++;
		if (this.cards[1].compareTo(this.cards[2]) == 0)
			edge++;
		if (edge == 1)
			return true;
		else
			return false;
	}

	private void sort() {
		// ���򣬴�С�����˳��
		Card t;
		if (this.cards[0].rank > this.cards[1].rank) {
			t = this.cards[0];
			this.cards[0] = this.cards[1];
			this.cards[1] = t;
		}
		if (this.cards[1].rank > this.cards[2].rank) {
			t = this.cards[1];
			this.cards[1] = this.cards[2];
			this.cards[2] = t;
		}
		if (this.cards[0].rank > this.cards[1].rank) {
			t = this.cards[0];
			this.cards[0] = this.cards[1];
			this.cards[1] = t;
		}
		if (this.cards[0].rank == 1)// �����Ace
		{
			t = this.cards[0];
			this.cards[0] = this.cards[2];
			this.cards[2] = t;
		}
	}

	// �ж��Ƿ�Ϊ˳��
	public boolean isStraight() {
		// �����򣬴�С�����˳��
		this.sort();
		// ���ж��Ƿ�Ϊ����Ϊ1�ĵȲ����л�����QKA˳��
		if ((this.cards[2].rank - this.cards[1].rank == 1)
				&& (this.cards[1].rank - this.cards[0].rank == 1))
			return true;
		else {
			if ((this.cards[0].rank == 1) && (this.cards[1].rank == 12)
					&& (this.cards[0].rank == 13))
				return true;
			else
				return false;
		}
	}

	// �ж��Ƿ�Ϊ�𻨡�ͬ��
	public boolean isFlush() {
		if (this.cards[0].suit == this.cards[1].suit
				&& this.cards[1].suit == this.cards[2].suit)
			return true;
		else
			return false;
	}

	// �ж��Ƿ�Ϊͬ��˳
	public boolean isStraighFlush() {
		if (this.isFlush() && this.isStraight())
			return true;
		else
			return false;
	}

	// �ж��Ƿ�Ϊը��
	public boolean isBoss() {
		if (this.cards[0].compareTo(this.cards[1]) == 0
				&& this.cards[1].compareTo(this.cards[2]) == 0)
			return true;
		else
			return false;
	}
}