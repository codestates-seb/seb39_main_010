import React, { useState } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';

const FooterContainer = styled.div`
	display: flex;
	justify-content: space-between;
	padding: 46px 170px;
	margin-top: 200px;

	width: 100%;
	height: 237px;

	background: #33343a;
	img {
		width: 40px;
	}
`;
const LeftSide = styled.div`
	> div:nth-child(1) {
		margin-bottom: 15px;

		font-weight: 400;
		font-size: 12px;

		color: white;

		span {
			margin-right: 20px;
		}
	}
	> div:nth-child(2),
	div:nth-child(3) {
		margin-bottom: 15px;

		font-weight: 400;
		font-size: 11px;

		color: ${theme.colors.gray300};
		span {
			margin-right: 10px;
		}
	}
`;
const RightSide = styled.div`
	display: flex;
	gap: 27px;
`;
const Footer = () => {
	return (
		<FooterContainer>
			<LeftSide>
				<div>
					<span>개인정보처리방침</span>
					<span>서비스 이용약관</span>
				</div>
				<div>
					<span>대표자</span> <span>|</span>{' '}
					<span>안태경 정혜선 민지원 장민욱 김민주</span>
				</div>
				<div>
					<span>logo</span>
					<span>© 2022 weply All rights reserved</span>
				</div>
			</LeftSide>
			<RightSide>
				<div>
					<a href="https://www.notion.so/codestates/Team-010-43af258ea9a14278aad853e7d9582990">
						<img src="https://platum.kr/wp-content/uploads/2020/06/Notion.png" />
					</a>
				</div>
				<div>
					<a href="https://github.com/codestates-seb/seb39_main_010">
						<img src="https://cdn-icons-png.flaticon.com/512/25/25231.png" />
					</a>
				</div>
			</RightSide>
		</FooterContainer>
	);
};
export default Footer;
