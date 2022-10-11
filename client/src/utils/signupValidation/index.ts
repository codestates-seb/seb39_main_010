export const regex = {
	email:
		/^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*.[A-Za-z]{2,3}$/,
	username: /^[A-Za-z0-9]{4,20}$/,
	password: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,20}$/,
	nickname: /^[A-Za-z0-9가-힣]{2,20}$/,
};

export const errorMessage = {
	email: '이메일 형식을 확인해주세요.',
	username: '6~12자 이내의 영문, 숫자로 입력해주세요.',
	password:
		'8~20자 이내로 영문 대소문자, 숫자, 특수문자를 포함하여 입력해주세요.',
	passwordConfirm: '입력하신 비밀번호와 일치하지 않습니다.',
	nickname: '2~20자 이내의 알파벳, 한글, 숫자로 입력해주세요.',
};
