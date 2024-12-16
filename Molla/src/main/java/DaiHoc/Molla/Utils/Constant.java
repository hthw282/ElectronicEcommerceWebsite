package DaiHoc.Molla.Utils;

public class Constant {
	public static int productPerPage = 16;
	public static enum eSortby{
		ASCENDING,
		POPULARITY,
		RATING,
		DATE
	}
	public static String vnp_ReturnUrl = "http://localhost:8081/vnpay-return";
	public static String productImageFile = "src/main/resources/static/assets/images/products";
	public static String productSubImageFile = "src/main/resources/static/assets/images/products/subpictures";
	public static String productZoomImageFile = "src/main/resources/static/assets/images/products/zoompicture";
	public static String manuImageFile = "src/main/resources/static/assets/images/manufacture";
	public static enum eRole{
		ADMIN,
		CUSTOMER
	}
	public static enum eStorageImage{
		PRODUCT,
		MANUFACTURER
	}
}
