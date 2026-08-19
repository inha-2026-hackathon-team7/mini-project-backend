# My Food Order

배달의 민족 클론 웹 서비스 미니프로젝트 백엔드 서버입니다.

---
## Tech Stack

- Java 21
- Spring Boot 4.1.0
- MySQL 8.0.42

---
## Requirements

- JDK 21
- IntelliJ IDEA
- MySQL Workbench
- Git

---
## Database
- Database name: food_order
- 이미지 파일은 Object Storage에 저장하고, DB에는 이미지 URL만 저장합니다.
- tables
  - `restaurants`: 음식점 정보
  - `restaurant_images`: 음식점 대표/상세 이미지
  - `menus`: 음식점별 메뉴 정보
  - `carts`: 세션 기반 장바구니
  - `cart_items`: 장바구니에 담긴 메뉴
  - `orders`: 주문 정보
  - `order_items`: 주문 당시 메뉴 스냅샷
  - `payments`: 결제 정보
---
## API
추후 작성 예정