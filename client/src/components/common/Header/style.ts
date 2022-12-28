import styled from 'styled-components';
import { theme } from 'styles/theme';

export const HeaderContainer = styled.div`
	position: relative;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0px 6%;
	gap: 2rem;

	width: 100%;
	height: 88px;

	box-shadow: inset 0px -1px 1px rgba(0, 0, 0, 0.1);

	.avatar-svg {
		width: 50px;
		height: 50px;
		cursor: pointer;
	}

	.login {
		color: ${theme.colors.gray700};
		font-weight: 600;
	}

	.login:hover {
		cursor: pointer;
	}
`;
export const LogoandNav = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 0px;
	gap: 60px;

	width: 604px;
	height: 34px;

	div {
		width: 134px;
		height: 34px;
		background-color: lightgray;
	}

	ul {
		display: flex;
		flex-direction: row;
		align-items: center;
		padding: 0px;
		gap: 30px;

		li:hover {
			color: #3563e9;
			font-weight: bold;
			cursor: pointer;
		}
		li.active {
			color: #3563e9;
			font-weight: bold;
		}
	}
`;
