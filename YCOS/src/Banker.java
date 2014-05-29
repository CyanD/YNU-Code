/**
 * ׯ�ң�����ϴ�ơ����ơ��Ƚ��˿��ƵĴ�С
 */

/**
 * @author HYC
 * 
 */
public class Banker extends Deck {
	private int playerNumb;
	 int eachSbuDeckNumb;// ������������
	
	public int getPlayerNumb() {
		return playerNumb;
	}

	public void setPlayerNumb(int playerNumb) {
		this.playerNumb = playerNumb;
	}

	public int getEachSbuDeckNumb() {
		return eachSbuDeckNumb;
	}

	public void setEachSbuDeckNumb(int eachSbuDeckNumb) {
		this.eachSbuDeckNumb = eachSbuDeckNumb;
	}

	public Banker() {
		// �������׼������
		super();
	}

	// Ϊÿ����ҷ���
	public Deck[] initPlayer() {
		if (eachSbuDeckNumb * playerNumb > 52) {
			System.out.println("Error!");
			return null;
		}
		else{
			Deck[] decks=new Deck[this.playerNumb];
			for(int i=0;i<this.playerNumb;i++){
				decks[i]=subDeck(i*3,i*3+this.eachSbuDeckNumb-1);
			}
			return decks;
		}
	}
	//����
	private Deck subDeck(int low,int high){
		Deck sub=new Deck(high-low+1);
		for(int i=0;i<=high-low;i++)
		{
			sub.cards[i]=this.cards[low+i];
		}
		return sub;
	}
	// ϴ��
	public void shuffle() {
		for (int i = 0; i < this.cards.length; i++) {
			// ����this.cards[i]��this.cards[randomInt(i,this.cards.length)]
			Card temp = new Card();
			int rnd = randomInt(i, this.cards.length - 1);
			temp = this.cards[i];
			this.cards[i] = this.cards[rnd];
			this.cards[rnd] = temp;
		}
	}

	private int randomInt(int low, int high) {
		return (int) Math.round((high - (high - low) * (1 - Math.random())));
	}
}