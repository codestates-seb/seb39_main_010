import styled from 'styled-components';
import { theme } from 'styles/theme';

// Card 시작

export const CardContainer = styled.div`
	width: 387px;
	height: 272px;

	padding: 18px 24px;

	background-color: white;
	border: 1px solid #d2d2d2;
	border-radius: 17px;
`;

export const Top = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;

	div:first-child {
		display: flex;
		justify-content: center;
		align-items: center;

		width: 64px;
		height: 29px;

		background: #3563e9;
		border-radius: 24px;
		font-size: 13px;
		font-weight: 500;
		color: white;
	}
`;
export const Middle = styled.div`
	div {
		h3 {
			display: flex;
			align-items: center;
			height: 55px;

			font-weight: 600;
			font-size: 20px;
			line-height: 27px;
			letter-spacing: -0.4px;

			margin: 6px 0;
			&:hover {
				cursor: pointer;
				color: #94caf3;
			}
		}
		div {
			font-family: 'Apple SD Gothic Neo';
			font-style: normal;
			font-weight: 400;
			font-size: 14px;
			line-height: 143%;

			color: #777777;
		}
	}
	p {
		width: 341px;
		height: 51px;
		padding: 7px 0;

		font-style: normal;
		font-size: 14px;
		line-height: 148%;
		color: #4f4f4f;

		overflow: hidden;
		white-space: normal;
		word-wrap: break-word;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;

		margin: 16px 0;
	}
`;

export const Bottom = styled.div`
	display: flex;
	justify-content: space-between;
	padding: 15px 0;

	border-top: 0.6px solid #dbdbdb;

	div {
		display: flex;
		align-items: center;
		gap: 10px;
	}
`;

// Card 끝
