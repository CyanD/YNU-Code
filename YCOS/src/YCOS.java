
public class YCOS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banker bk=new Banker();//初始化对象，空构造方法会生成52张扑克牌
		bk.shuffle();//洗牌，以使得扑克牌是随机的
		System.out.println("全部扑克牌：");
		bk.print();
		bk.setEachSbuDeckNumb(3);
		bk.setPlayerNumb(10);//玩家数量，如果是炸金花那么52/3=17，故不能超过17人玩
		System.out.println("总张数：");
		System.out.println(bk.cards.length);
		
		Deck[] decks=bk.initPlayer();//初始化每个玩家
		System.out.println("");
		for(int i=0;i<decks.length;i++){//为玩家发牌
			System.out.println("");
			System.out.println("----------玩家"+i+"的手牌----------");
			decks[i].print();
		}

		System.out.println("---------------------------------");
		System.out.println("|比较结果：                                                    |");
		System.out.println("|              "+
		decks[0].compareSubDeckTo(decks[1])   +"               |");
		System.out.println("---------------------------------");
		
		Judge J=new Judge(decks);
		J.selectionSort();
		System.out.println("");
		System.out.println("---------扑克牌按从小到大顺序排序---------");
		for(int i=0;i<J.getDeck().length;i++){//为玩家发牌
			System.out.println("----------玩家"+i+"的手牌----------");
			J.getDeck()[i].print();
		}
		
	}
	}

 