export interface ChangePasswordForm {
  email: string;
  confirmationToken: string;
  newPassword: string;
  confirmPassword: string;
}
