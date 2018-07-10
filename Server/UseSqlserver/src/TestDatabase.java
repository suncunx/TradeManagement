import java.sql.Connection;
import java.util.List;

import com.trade.model.Goods;
import com.trade.util.DbUtil;
import com.trade.util.Util;


public class TestDatabase {

	public static void main(String[] args) throws Exception {
		DbUtil util = new DbUtil();
		Connection con = util.getCon();
		List<Goods> goodss = util.listGoods(con, "1");
		for (Goods goods : goodss) {
			System.out.println(goods);
		}
		
		System.out.println(Util.getYearDate(3));
		System.out.println(Util.getDate(2));
		
//		util.listInBills(con, "1");
		
	}
}
