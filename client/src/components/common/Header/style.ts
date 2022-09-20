import styled from 'styled-components';

export const HeaderContainer = styled.div`
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	padding: 0px 6%;
	gap: 2rem;

	width: 100vw;
	height: 88px;

	/* background: #ffffff; */
	box-shadow: inset 0px -1px 1px rgba(0, 0, 0, 0.1);
`;
export const LogoandNav = styled.div`
	/* Auto layout */

	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 0px;
	gap: 60px;

	width: 604px;
	height: 34px;

	/* Inside auto layout */

	div {
		/* logo */

		width: 134px;
		height: 34px;
		background-color: lightgray;

		/* Inside auto layout */

		flex: none;
		order: 0;
		flex-grow: 0;
	}

	ul {
		/* Auto layout */

		display: flex;
		flex-direction: row;
		align-items: center;
		padding: 0px;
		gap: 30px;

		/* Inside auto layout */

		flex: none;
		order: 1;
		flex-grow: 0;

		li:hover {
			color: #3563e9;
			font-weight: bold;
			cursor: pointer;
		}
	}
`;
export const MypageImg = styled.div`
	/* Avatar */

	width: 50px;
	height: 50px;

	/* systemGrey/100 */

	background: #f5f5f5;
	border-radius: 30px;

	/* Inside auto layout */

	flex: none;
	order: 1;
	flex-grow: 0;
`;
