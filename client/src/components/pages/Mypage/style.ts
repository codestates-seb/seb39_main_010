import styled from 'styled-components';
import { theme } from 'styles/theme';

// Introduction start
export const IntroductionContainer = styled.div`
	display: flex;
	flex-direction: column;
	gap: 30px;

	align-items: center;
	width: 800px;

	button {
		display: flex;
		width: 120px;
		height: 45px;
		padding: 0;
		font-size: 14px;
		gap: 5px;
	}
`;

export const ProfileText = styled.div`
	display: flex;
	flex-direction: column;
	gap: 25px;
	width: 100%;

	.nicknamecompany {
		display: flex;
		justify-content: space-between;
		align-items: center;

		#nickname {
			font-size: 36px;
		}

		.buttons {
			display: flex;
			gap: 15px;
		}
	}
	#company {
		display: flex;
		align-items: center;
		font-size: 18px;
		color: ${theme.colors.gray500}; //gray500
		span {
			margin-right: 15px;
			flex-shrink: 0;
			:last-child {
				color: ${theme.colors.gray400}; //gray400
			}
		}
	}
	.text {
		padding: 10px 15px;
		border: 1px solid #ebebeb;
		border-radius: 15px;
	}

	input {
		margin: 0;
		padding: 10px 15px;
		border: 1px solid #ebebeb;
		border-radius: 5px;
		width: 100%;
	}
`;
export const ProfileImg = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;

	width: 170px;
	height: 170px;

	background: #d9d9d9;
	border-radius: 999px;
`;
