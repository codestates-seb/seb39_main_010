export interface SignupSubmitForm {
	email: string;
	username: string;
	password: string;
	nickname: string;
}

export interface LoginSubmitForm {
	username: string;
	password: string;
}

export interface User {
	memberId: number;
	username: string;
	email: string;
	nickname: string;
}
