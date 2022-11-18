import React, { HTMLInputTypeAttribute } from 'react';
import { InputProps } from 'components/common/Input/Input';
import styled from 'styled-components';

interface AuthInputProps extends InputProps {
	type?: HTMLInputTypeAttribute;
	placeholder: string;
	name?: string;
	value?: string;
	onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
	label?: string;
	errorMessage?: string;
	mode?: 'signup' | 'login';
	bgColor?: string;
	border?: string;
}

interface AuthInputOptions {
	[key: string]: {
		bgColor: AuthInputProps['bgColor'];
		border: AuthInputProps['border'];
	};
}

const AuthInputOptions: AuthInputOptions = {
	signup: {
		bgColor: 'white',
		border: '1px solid #d2d5da',
	},
	login: {
		bgColor: '#F8F8F8',
		border: 'none',
	},
};

const AuthInput = React.forwardRef<HTMLInputElement, AuthInputProps>(
	(
		{
			type = 'text',
			placeholder,
			name,
			value,
			onChange,
			label,
			errorMessage,
			mode = 'signup',
			...props
		}: AuthInputProps,
		ref
	) => {
		const { bgColor, border } = AuthInputOptions[mode];

		return (
			<InputContainer>
				{label && <label>{label}</label>}
				<StyledInput
					type={type}
					placeholder={placeholder}
					name={name}
					value={value}
					onChange={onChange}
					mode={mode}
					bgColor={bgColor}
					border={border}
					ref={ref}
					{...props}
				/>
				<p>{errorMessage}</p>
			</InputContainer>
		);
	}
);

AuthInput.displayName = 'AuthInput';

export default AuthInput;

const InputContainer = styled.div`
	display: flex;
	flex-direction: column;
	width: 100%;

	& label {
		font-size: 18px;
		margin-bottom: 12px;
	}

	& p {
		color: red;
		font-size: 12px;
	}
`;

const StyledInput = styled.input<AuthInputProps>`
	width: 100%;
	padding: 10px 20px;
	border: ${(props) => props.border};
	background-color: ${(props) => props.bgColor};
	font-size: 16px;
	border-radius: 10px;

	& ::placeholder {
		color: #949494;
	}
`;
