/**
 * 裁判，用来判断大小，期望，概率，等
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

	//public Card average() {// 扑克牌大小的期望

	//}

	public void selectionSort() {// 从小到达的顺序排序，初步考虑使用最简单的选择排序
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
