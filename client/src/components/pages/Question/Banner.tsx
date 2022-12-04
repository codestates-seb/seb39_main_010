import React from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { ReactComponent as BannerImg } from 'assets/images/banner.svg';

const Banner = () => {
	return (
		<BannerContainer>
			<BannerImg />
		</BannerContainer>
	);
};

export default Banner;

const BannerContainer = styled.div`
	width: 100%;
	height: 400px;
	background-color: ${theme.colors.blueMain};
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

	& button {
		padding: 15px 50px;
		border-radius: 0;
		width: auto;
		font-size: 18px;
		font-weight: normal;
		height: 52px;
		margin-top: 50px;
	}
`;
