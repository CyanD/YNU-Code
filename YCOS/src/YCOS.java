
public class YCOS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banker bk=new Banker();//��ʼ�����󣬿չ��췽��������52���˿���
		bk.shuffle();//ϴ�ƣ���ʹ���˿����������
		System.out.println("ȫ���˿��ƣ�");
		bk.print();
		bk.setEachSbuDeckNumb(3);
		bk.setPlayerNumb(10);//��������������ը����ô52/3=17���ʲ��ܳ���17����
		System.out.println("��������");
		System.out.println(bk.cards.length);
		
		Deck[] decks=bk.initPlayer();//��ʼ��ÿ�����
		System.out.println("");
		for(int i=0;i<decks.length;i++){//Ϊ��ҷ���
			System.out.println("");
			System.out.println("----------���"+i+"������----------");
			decks[i].print();
		}

		System.out.println("---------------------------------");
		System.out.println("|�ȽϽ����                                                    |");
		System.out.println("|              "+
		decks[0].compareSubDeckTo(decks[1])   +"               |");
		System.out.println("---------------------------------");
		
		Judge J=new Judge(decks);
		J.selectionSort();
		System.out.println("");
		System.out.println("---------�˿��ư���С����˳������---------");
		for(int i=0;i<J.getDeck().length;i++){//Ϊ��ҷ���
			System.out.println("----------���"+i+"������----------");
			J.getDeck()[i].print();
		}
		
	}
	}

 