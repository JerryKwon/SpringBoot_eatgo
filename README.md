# Spring Boot를 활용한 레스토랑 예약사이트 만들기

* 서비스의 주요 목적
	1. 주변에 어떤 가게가 있는지 찾아준다.
	2. 주변 가게들에 대한 평점 / 평가 실시 및 열람.
	3. 가고 싶은 가게들을 찜할 수 있어야 한다.
	4. 가게에 있는 잔여좌석 확인
	5. 온라인 예약 기능
	
* 사용자 스토리

: 사용자 입장에서 프로그램 및 시스템의 기능을 서술

**ex) (사용자)는 (가치)를 위해 (기능)을 할 수 있다.**

* 사용자는 누구인가?
	1. 서비스를 사용하려는 고객
	2. 가게를 홍보하려는 가게
	3. 이를 관리하는 관리자
	
* 사용자 스토리 정리

	**고객**
	1. 고객은 **뭘 먹고 싶은지 발견**할 수 있도록 가게 목록을 볼 수 있다.
	2. 고객은 **정확히 먹고 싶은 것이 뭔지 알 수 있도록** 가게의 메뉴를 볼 수 있다.
	3. 고객은 **좋은 가게인지 알 수 있도록** 평점을 확인할 수 있다.
	4. 고객은 **선택의 폭을 좁히기 위해** 가게 목록을 분류에 따라 볼 수 있다.
	5. 고객은 **나와 남을 위해서** 가게에 평점을 남길 수 있다.
	6. 고객은 **나중에 찾아보기 쉽도록** 가게를 즐겨찾기에 추가할 수 있다.
	7. 고객은 **가게에서 기다리지 않기 위해** 가게에 인원, 메뉴를 예약할 수 있다.
	
	**가게**
	1. 가게는 관심있는 고객을 받기 위해 예약 요청을 볼 수 있다.
	2. 가게는 더 나은 고객 서비스를 위해 예약 요청에 응답할 수 있다.
	
	**관리자**
	1. 관리자는 고객이 서비스를 쓸 수 있도록 가게 정보를 등록할 수 있다.
	
* 시스템의 구조

: **3 Tier Architecture**

	1. Presentation: 사용자와 어떻게 소통할 것인지를 정의 -- Front End HTML/CSS/JS
	2. Business: 처리된 작업들에 대해서 어떻게 분산하고 처리 방식을 결정할 것인지 -- Back End REST API
	3. Data Source -- DBMS

	**Layerd Architecture(in Back End)**
	1. UI Layer(어플리케이션 동작에 원천적인 작업에 집중할 수 있도록 처리)
	=> Interfaces(controller)

	2. Application Layer => 웹에 대한 처리를 담당함(웹이 어떻게 작동하는지)
	=> application(restaurantService)

			Restaurant restaurant = restaurantRepository.findById(id);

			List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

			restaurant.setMenuItems(menuItems);

	와 같이 Controller 에서 수행하는 로직이 복잡해 짐으로 인해 Application Layer를 담당할 클래스인 Service 클래스를 생성하여 그 내부에서 리포지토리를 통한 작업이 일어날 수 있도록 처리.

	3. Domain Layer
	=> domains(entity,repository)
	
	4. Infrastructure Layer
	
	: 상위 레이어는 하위 레이어들을 사용하지만, 하위 레이어는 상위 레이어에 접근하고 사용할 수 없다.
	
* 테스트 중심 개발(== "목표 주도 개발", "사용자 중심 개발", "인터페이스 중심 개발")

	* 목적
	
	: 올바르게 작동하는 코드를 개발,깔끔한 코드 생성하기 위해
	=> Refactoring
	
	* **올바르게 작동하는 코드**
	
	: 이를 달성하기 위해서, Test Code가 중심이 되는 개발이 되어야 한다.

	* TDD Cycle
		1. Red
		2. Green
		3. Refactoring

* Rest API (REPresentational State Transfer)

: 표현 상태를 전달하는 것.

	* Resource를 처리하는 방식
	
	 Resource 흐름 -> HTTP 명령
	1. Create -> Post 
	2. Read -> Get
	3. Update -> Put / Patch
	4. Delete -> Delete
	
	* URI / URL
	
	: 리소스를 지정할 떄 사용함.
	
	* Resource는
	
	Collection과 Member로 구분
	
		* Collection
		
		: Read(List), Create
		
		* Member
		
		: Read(Detail), Update, Delete
	