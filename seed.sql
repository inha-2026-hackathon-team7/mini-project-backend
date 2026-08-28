SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE menus;
TRUNCATE TABLE restaurants;

-- ============================================
-- Restaurants Seed Data
-- ============================================

INSERT INTO restaurants (
    delivery_fee,
    is_open,
    minimum_order_amount,
    created_at,
    updated_at,
    name,
    description
) VALUES
(
    3000,
    b'1',
    15000,
    NOW(6),
    NOW(6),
    '치킨마루',
    '바삭한 후라이드와 다양한 소스를 즐길 수 있는 치킨 전문점입니다.'
),
(
    2500,
    b'1',
    12000,
    NOW(6),
    NOW(6),
    '한끼뚝딱 김밥',
    '김밥, 분식, 라면을 빠르게 즐길 수 있는 분식 전문점입니다.'
),
(
    3000,
    b'1',
    18000,
    NOW(6),
    NOW(6),
    '불타는 고기집',
    '직화 불맛을 살린 제육볶음과 덮밥 전문점입니다.'
),
(
    0,
    b'0',
    10000,
    NOW(6),
    NOW(6),
    '오늘의 파스타',
    '신선한 재료로 만드는 파스타와 리조또 전문점입니다.'
),
(
    2000,
    b'1',
    15000,
    NOW(6),
    NOW(6),
    '버거팩토리',
    '수제 패티와 다양한 토핑을 사용하는 수제버거 전문점입니다.'
);

-- ============================================
-- Menus Seed Data
-- ============================================

-- --------------------------------------------
-- 1. 치킨마루
-- --------------------------------------------

INSERT INTO menus (
    is_available,
    price,
    created_at,
    menu_id,
    restaurant_id,
    updated_at,
    name,
    description,
    image_url
) VALUES
(
    b'1',
    18000,
    NOW(6),
    DEFAULT,
    1,
    NOW(6),
    '후라이드 치킨',
    '바삭하고 담백한 기본 후라이드 치킨',
    'https://example.com/images/fried-chicken.jpg'
),
(
    b'1',
    19000,
    NOW(6),
    DEFAULT,
    1,
    NOW(6),
    '양념 치킨',
    '매콤달콤한 특제 양념 치킨',
    'https://example.com/images/spicy-chicken.jpg'
),
(
    b'1',
    20000,
    NOW(6),
    DEFAULT,
    1,
    NOW(6),
    '간장 치킨',
    '짭조름하고 달콤한 간장 소스 치킨',
    'https://example.com/images/soy-chicken.jpg'
),
(
    b'1',
    21000,
    NOW(6),
    DEFAULT,
    1,
    NOW(6),
    '반반 치킨',
    '후라이드와 양념을 한 번에 즐기는 반반 치킨',
    'https://example.com/images/half-chicken.jpg'
),
(
    b'1',
    5000,
    NOW(6),
    DEFAULT,
    1,
    NOW(6),
    '치즈볼',
    '쫄깃한 반죽 안에 치즈가 들어간 사이드 메뉴',
    'https://example.com/images/cheese-ball.jpg'
);


-- --------------------------------------------
-- 2. 한끼뚝딱 김밥
-- --------------------------------------------

INSERT INTO menus (
    is_available,
    price,
    created_at,
    restaurant_id,
    updated_at,
    name,
    description,
    image_url
) VALUES
(
    b'1',
    4000,
    NOW(6),
    2,
    NOW(6),
    '야채 김밥',
    '신선한 야채를 듬뿍 넣은 기본 김밥',
    'https://example.com/images/vegetable-gimbap.jpg'
),
(
    b'1',
    5500,
    NOW(6),
    2,
    NOW(6),
    '참치 김밥',
    '고소한 참치와 마요네즈가 들어간 김밥',
    'https://example.com/images/tuna-gimbap.jpg'
),
(
    b'1',
    6000,
    NOW(6),
    2,
    NOW(6),
    '불고기 김밥',
    '달콤짭짤한 불고기를 넣은 김밥',
    'https://example.com/images/bulgogi-gimbap.jpg'
),
(
    b'1',
    4500,
    NOW(6),
    2,
    NOW(6),
    '떡볶이',
    '매콤달콤한 국물 떡볶이',
    'https://example.com/images/tteokbokki.jpg'
),
(
    b'1',
    3000,
    NOW(6),
    2,
    NOW(6),
    '라면',
    '얼큰하고 따뜻한 기본 라면',
    'https://example.com/images/ramen.jpg'
),
(
    b'0',
    3500,
    NOW(6),
    2,
    NOW(6),
    '치즈 라면',
    '치즈를 듬뿍 올린 고소한 라면',
    'https://example.com/images/cheese-ramen.jpg'
);


-- --------------------------------------------
-- 3. 불타는 고기집
-- --------------------------------------------

INSERT INTO menus (
    is_available,
    price,
    created_at,
    restaurant_id,
    updated_at,
    name,
    description,
    image_url
) VALUES
(
    b'1',
    11000,
    NOW(6),
    3,
    NOW(6),
    '직화 제육덮밥',
    '직화로 볶아 불맛을 살린 제육덮밥',
    'https://example.com/images/jeyuk-rice.jpg'
),
(
    b'1',
    12000,
    NOW(6),
    3,
    NOW(6),
    '불고기 덮밥',
    '달콤짭짤한 소불고기 덮밥',
    'https://example.com/images/bulgogi-rice.jpg'
),
(
    b'1',
    13000,
    NOW(6),
    3,
    NOW(6),
    '매운 제육볶음',
    '매운 양념으로 볶아낸 제육볶음',
    'https://example.com/images/spicy-jeyuk.jpg'
),
(
    b'1',
    4000,
    NOW(6),
    3,
    NOW(6),
    '계란찜',
    '부드럽고 촉촉한 계란찜',
    'https://example.com/images/steamed-egg.jpg'
),
(
    b'1',
    2000,
    NOW(6),
    3,
    NOW(6),
    '공기밥',
    '국내산 쌀로 지은 밥',
    NULL
);


-- --------------------------------------------
-- 4. 오늘의 파스타
-- 현재 영업 종료 상태
-- --------------------------------------------

INSERT INTO menus (
    is_available,
    price,
    created_at,
    restaurant_id,
    updated_at,
    name,
    description,
    image_url
) VALUES
(
    b'1',
    14000,
    NOW(6),
    4,
    NOW(6),
    '토마토 파스타',
    '진한 토마토 소스로 만든 클래식 파스타',
    'https://example.com/images/tomato-pasta.jpg'
),
(
    b'1',
    15000,
    NOW(6),
    4,
    NOW(6),
    '크림 파스타',
    '부드럽고 진한 크림 소스 파스타',
    'https://example.com/images/cream-pasta.jpg'
),
(
    b'1',
    16000,
    NOW(6),
    4,
    NOW(6),
    '로제 파스타',
    '토마토와 크림 소스가 조화로운 로제 파스타',
    'https://example.com/images/rose-pasta.jpg'
),
(
    b'1',
    17000,
    NOW(6),
    4,
    NOW(6),
    '해산물 오일 파스타',
    '신선한 해산물과 올리브 오일로 만든 파스타',
    'https://example.com/images/seafood-pasta.jpg'
),
(
    b'0',
    18000,
    NOW(6),
    4,
    NOW(6),
    '트러플 리조또',
    '트러플 향을 더한 크리미한 리조또',
    'https://example.com/images/truffle-risotto.jpg'
);


-- --------------------------------------------
-- 5. 버거팩토리
-- --------------------------------------------

INSERT INTO menus (
    is_available,
    price,
    created_at,
    restaurant_id,
    updated_at,
    name,
    description,
    image_url
) VALUES
(
    b'1',
    8500,
    NOW(6),
    5,
    NOW(6),
    '클래식 버거',
    '소고기 패티와 신선한 채소로 구성된 기본 버거',
    'https://example.com/images/classic-burger.jpg'
),
(
    b'1',
    9500,
    NOW(6),
    5,
    NOW(6),
    '치즈 버거',
    '두툼한 패티 위에 체다치즈를 올린 버거',
    'https://example.com/images/cheese-burger.jpg'
),
(
    b'1',
    10500,
    NOW(6),
    5,
    NOW(6),
    '베이컨 버거',
    '바삭한 베이컨과 소고기 패티가 들어간 버거',
    'https://example.com/images/bacon-burger.jpg'
),
(
    b'1',
    5500,
    NOW(6),
    5,
    NOW(6),
    '감자튀김',
    '바삭하게 튀긴 프렌치프라이',
    'https://example.com/images/french-fries.jpg'
),
(
    b'1',
    3000,
    NOW(6),
    5,
    NOW(6),
    '콜라',
    '시원한 탄산음료',
    NULL
);

SET FOREIGN_KEY_CHECKS = 1;