import React, { HTMLInputTypeAttribute, InputHTMLAttributes } from 'react';
import styled from 'styled-components';

// TODO: 추후 최종적으로 필요없으면 style props 관련 주석 삭제 필요
// interface StyleProps {
// 	width?: string;
// 	height?: string;
// 	marginBottom?: string;
// }

export interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
	type?: HTMLInputTypeAttribute;
	placeholder: string;
	name?: string;
	value?: string;
	onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
	label?: string;
	// style?: StyleProps;
}

const Input = React.forwardRef<HTMLInputElement, InputProps>(
	(
		{
			type = 'text',
			placeholder,
			name,
			value,
			onChange,
			label,
			...props
		}: InputProps,
		ref
	) => {
		return (
			<InputContainer>
				{label && <label>{label}</label>}
				<StyledInput
					type={type}
					placeholder={placeholder}
					name={name}
					value={value}
					onChange={onChange}
					ref={ref}
					{...props}
				/>
			</InputContainer>
		);
	}
);

Input.displayName = 'Input';

export default Input;

const InputContainer = styled.div`
	display: flex;
	flex-direction: column;
	width: 100%;

	& label {
		font-size: 18px;
		margin-bottom: 18px;
	}
`;

const StyledInput = styled.input<InputProps>`
	width: 100%;
	height: 66px;
	margin-bottom: '18px';
	padding: 10px 20px;
	border: 1px solid #d2d5da;
	background-color: white;
	font-size: 18px;
	border-radius: 10px;

	:focus {
		outline: none !important;
		border-color: #94caf3;
		box-shadow: 0 0 0px 4px #d6edfc;
	}

	& ::placeholder {
		color: #949494;
	}
`;
