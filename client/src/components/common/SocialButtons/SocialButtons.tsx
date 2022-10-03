import { SocialLogos } from 'assets/images/SocialLogos';
import React, { ButtonHTMLAttributes } from 'react';
import styled from 'styled-components';

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
	bgColor?: string;
	border?: string;
	mode: 'google' | 'naver' | 'kakao';
	onClick?: () => void;
}

interface SocialButtonOptions {
	[key: string]: {
		bgColor: Props['bgColor'];
		border: Props['border'];
		logoImg: string;
	};
}

const SocialButtonOptions: SocialButtonOptions = {
	google: {
		bgColor: 'white',
		border: '1px solid #F3F4F6',
		logoImg: SocialLogos.googleLogo,
	},
	naver: {
		bgColor: '#59C250',
		border: 'none',
		logoImg: SocialLogos.naverLogo,
	},
	kakao: {
		bgColor: '#FEE500',
		border: 'none',
		logoImg: SocialLogos.kakaoLogo,
	},
};

const SocialButton = ({ mode, ...props }: Props) => {
	const { bgColor, border, logoImg } = SocialButtonOptions[mode];
	return (
		<Button mode={mode} bgColor={bgColor} border={border} {...props}>
			<img src={logoImg} alt={`${mode}Logo`} />
		</Button>
	);
};

export default SocialButton;

const Button = styled.button<Props>`
	border: ${(props) => props.border};
	width: 60px;
	height: 60px;
	background-color: ${(props) => props.bgColor};
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	border-radius: 50%;
	& img {
		height: 25px;
	}
`;
