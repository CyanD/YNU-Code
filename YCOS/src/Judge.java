/**
 * ���У������жϴ�С�����������ʣ���
 */

/**
 * @author HYC
 * 
 */
public class Judge extends Deck {
	private Deck[] decks;

	public Judge(Deck[] deck) {
		super();
		this.decks = deck;
	}

	public Judge() {
		super();
		this.decks = null;
	}

	public Deck[] getDeck() {
		return decks;
	}

	public void setDeck(Deck[] deck) {
		this.decks = deck;
	}

	//public Card average() {// �˿��ƴ�С������

	//}

	public void selectionSort() {// ��С�����˳�����򣬳�������ʹ����򵥵�ѡ������
		int len = this.decks.length;
		Deck temp;
		for (int i = 0; i <= len - 2; i++) {
			DeckandIndex deckandIndex=this.getMinDeck(i, len - 1);
			temp=this.decks[i];
			this.decks[i] = deckandIndex.deck;
			this.decks[deckandIndex.index]=temp;
		}
	}

	private DeckandIndex getMinDeck(int low, int high) {
		DeckandIndex deckandIndex=new DeckandIndex();
		int len = high - low + 1;
		Deck result = this.decks[low];int index=0;
		for (int i = low; i < len; i++) {
			if (result.compareSubDeckTo(this.decks[i]) == 1) {
				result = this.decks[i];
				index=i;
			}

		}
		deckandIndex=new DeckandIndex(result,index);
		return deckandIndex;
	}

}
