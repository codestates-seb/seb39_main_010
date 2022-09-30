import styled from 'styled-components';
import { theme } from 'styles/theme';

// Introduction start
export const IntroductionContainer = styled.div`
	display: flex;
	align-items: center;
	width: 1200px;
	height: 205px;
	margin: 44px 0;

	button {
		width: 81px;
		height: 45px;
		padding: 0;

		font-size: 14px;
	}
`;

export const Empty = styled.div`
	width: 200px;
`;

export const Profile = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 1000px;
	height: 170px;
`;

export const ProfileText = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	height: 100%;

	.nicknamecompany {
		display: flex;
		justify-content: space-between;
		align-items: center;

		#nickname {
			font-size: 36px;
		}
	}
	#company {
		display: flex;
		align-items: center;
		font-size: 18px;
		color: ${theme.colors.gray500}; //gray500
		span {
			margin-right: 15px;
			:last-child {
				color: ${theme.colors.gray400}; //gray400
			}
		}
	}
	.text {
		width: 752px;
		height: 79px;
		padding: 5px 10px;

		border: 1px solid #ebebeb;
		border-radius: 15px;
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

// Introduction end
