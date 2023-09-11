import { Component, OnInit } from '@angular/core';
import { AngularSvgIconModule } from 'angular-svg-icon';
import { Router, RouterLink } from '@angular/router';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthenticationService } from 'src/app/core/services/AuthentificationService/authentication-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLink, AngularSvgIconModule, CommonModule],
})
export class SignUpComponent implements OnInit {
  form!: FormGroup; // Déclarez signUpForm comme un FormGroup
  hidePassword = true;
  passwordTextType!: boolean;
  confirmPasswordTextType!: boolean;
  loginPasswordVisible = false;
  signupPasswordVisible = false;
  signupConfirmPasswordVisible = false;

  constructor(private _formBuilder: FormBuilder, private authService: AuthenticationService, private router: Router) {
    // Initialisez signUpForm ici
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)]),
      confirmPassword: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.form = this._formBuilder.group(
      {
        email: ['', [Validators.required, Validators.email]],
        nom: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', Validators.required],
      },
      {
        validator: this.passwordMatchValidator, // Add this line to use the validator
      },
    );
  }

  get formControls() {
    return {
      nom: this.form.get('nom') as FormControl,
      email: this.form.get('email') as FormControl,
      password: this.form.get('password') as FormControl,
      confirmPassword: this.form.get('confirmPassword') as FormControl,
    };
  }

  onSubmit() {
    if (this.form.invalid) {
      console.error('Le formulaire est invalide');
      console.log('Form errors:', this.form.errors); // Add this line for debugging
      console.log('Email errors:', this.formControls.email.errors);
      console.log('Nom errors:', this.formControls.nom.errors);
      console.log('Password errors:', this.formControls.password.errors);
      console.log('ConfirmPassword errors:', this.formControls.confirmPassword.errors);

      return;
    }

    const nom = this.formControls['nom'].value;
    const email = this.formControls['email'].value;
    const password = this.formControls['password'].value;

    // Envoyez les données au service d'authentification pour l'inscription
    this.authService.signUp(nom, email, password).subscribe(
      (response) => {
        // Gérez la réponse ici, par exemple, redirigez l'utilisateur vers la page de connexion
        this.router.navigate(['/dashboard']);

        console.log('Inscription réussie', response);
      },
      (error) => {
        // Gérez les erreurs ici, par exemple, affichez un message d'erreur à l'utilisateur
        console.error("Erreur d'inscription", error);
      },
    );
  }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }
  // Inside your component class
  togglePasswordVisibility() {
    const passwordControl = this.form.get('password');
    if (passwordControl) {
      const currentType = passwordControl.get('type')?.value;
      passwordControl.get('type')?.setValue(currentType === 'text' ? 'password' : 'text');
    }
  }

  toggleLoginPasswordVisibility() {
    this.loginPasswordVisible = !this.loginPasswordVisible;
  }

  toggleSignupPasswordVisibility() {
    this.signupPasswordVisible = !this.signupPasswordVisible;
  }

  toggleSignupConfirmPasswordVisibility() {
    this.signupConfirmPasswordVisible = !this.signupConfirmPasswordVisible;
  }

  toggleConfirmPasswordTextType() {
    this.confirmPasswordTextType = !this.confirmPasswordTextType;
  }
}
