- Cách để kill port:
+ Appium chạy trên nền nodejs để đơn giản hóa nhất thì trên mac dùng killall còn trên window là taskkill /F /IM.node.exe
+ Wait:
+ ImplicitWait: Chờ ngầm định (Cho cả 1 driver session)
- Áp dụng cho toàn bộ session mình khởi tạo (tất cả các findElement Method bị apply)
- Thời gian tối đa tôi apply cho chuyện tìm các Element là 30s (500ms tìm 1 lần)
- Khi nào thấy Element thì continue... (next step. Tìm thấy trước 30s thì return về ElementID), không thấy thì tối đa chờ 30s trước khi throw ra lỗi No such element
+ ExplicitWait: Chờ tường minh (chờ trên đối tượng cụ thể)
- Trên màn hình của mình ở 1 thời điểm nào đó n có 1 vài đối tượng mà đối vs những chỗ khác nó sẽ lâu hơn bình thường. Ví dụ project 
là ecommerc thì cái bước thanh toán n pải gọi đến bên thứ 3> bước này quan trọng nên sẽ lâu hơn thao tác khác thì trong project mình
sẽ đặt chờ tối đa 45s > Dùng explicit wait> Chờ trên đối tượng tường minh cụ thể


🧭 1️⃣ Selector Strategies trong Appium
Nền tảng	Cú pháp dùng trong code	Ví dụ	Giải thích
1. accessibility id	By.accessibilityId("login_button")	driver.findElement(MobileBy.AccessibilityId("login_button"))	Dùng thuộc tính content-desc (Android) hoặc accessibilityLabel (iOS). Rất nhanh và ổn định nếu dev gắn id chuẩn.
2. id	By.id("com.example:id/username")	driver.findElement(By.id("com.app:id/btnLogin"))	Dựa trên resource-id (Android) hoặc name (iOS). Ưu tiên nếu app có id cố định.
3. class name	By.className("android.widget.Button")	driver.findElement(By.className("android.widget.EditText"))	Tìm theo loại phần tử (Button, TextView…). Có thể trả nhiều kết quả.
4. xpath	By.xpath("//android.widget.Button[@text='Login']")	driver.findElement(By.xpath("//android.widget.TextView[@text='Hello']"))	Linh hoạt, mạnh mẽ, hỗ trợ quan hệ cha–con, nhưng chậm và dễ gãy.
5. Android UIAutomator	MobileBy.AndroidUIAutomator("new UiSelector().text(\"Login\")")	driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").text(\"Login\")"))	Dành riêng cho Android — có thể dùng thuộc tính nâng cao như .textContains(), .resourceId(), .clickable(true), .index(), v.v.
6. iOS Predicate String	MobileBy.iOSNsPredicateString("name == 'login_button'")	driver.findElement(MobileBy.iOSNsPredicateString("label == 'Login'"))	Dành riêng cho iOS, dùng cú pháp NSPredicate (giống query trong Swift).
7. iOS Class Chain	MobileBy.iOSClassChain("**/XCUIElementTypeButton[name == 'Login']")	driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeTextField[value == 'Email']"))	Nhanh hơn XPath trên iOS, mô phỏng cấu trúc phân cấp UI.