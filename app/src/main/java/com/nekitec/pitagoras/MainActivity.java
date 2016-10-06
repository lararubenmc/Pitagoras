package com.nekitec.pitagoras;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.R.attr.format;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;


public class MainActivity extends AppCompatActivity {
    //Variables de la clase
    public Button abutton;
    public Button bbutton;
    public Button cbutton;
    public Button Abutton;
    public Button Bbutton;
    public Button Cbutton;
    public TextView TipoTriangulo;
    public ImageView triangulo;
    public String dato;
    int OK = 0;
    double a = 0, b = 0, c = 0, A = 0, B = 0, C = 0;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Asignación de nombres del layout
        abutton = (Button) findViewById(R.id.aButton);
        bbutton = (Button) findViewById(R.id.bButton);
        cbutton = (Button) findViewById(R.id.cButton);
        Abutton = (Button) findViewById(R.id.AButton);
        Bbutton = (Button) findViewById(R.id.BButon);
        Cbutton = (Button) findViewById(R.id.CButton);
        TipoTriangulo = (TextView) findViewById(R.id.TipoTriangulo);
        triangulo = (ImageView) findViewById(R.id.triangulo_calcular);
        bbutton.setText(R.string.N90);
        bbutton.setEnabled(false);

    }

    //Acciones de los botones
    public void aAngulo(View view) {
        CapturaForm(1);

    }

    public void bAngulo(View view) {
        CapturaForm(2);

    }

    public void cAngulo(View view) {
        CapturaForm(3);

    }

    public void ALado(View view) {
        CapturaForm(4);
    }

    public void BLado(View view) {
        CapturaForm(5);
    }

    public void CLado(View view) {
        CapturaForm(6);
    }


    //Frame para captura de datos tipo tipo numero para asignar velor en el texto del boton
    public void CapturaForm(final int TipoDato) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.datacapture, null, false);
        final EditText nameEditText = (EditText) formElementsView
                .findViewById(R.id.DatosEditText);
        new AlertDialog.Builder(this).setView(formElementsView)
                .setTitle(R.string.Captura)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(!nameEditText.getText().toString().equals(""))
                        {
                            dato = nameEditText.getText().toString();
                           llenar(TipoDato, dato);
                        }
                        dialog.cancel();

                    }
                }).show();
    }



    //cambia el texto en cada boton
    private void llenar(int IdentidadDato, String Valor) {
        switch (IdentidadDato) {
            case 1:
                abutton.setText(Valor);
                Verificar();
                break;
            case 2:
                bbutton.setText(Valor);
                Verificar();
                break;
            case 3:
                cbutton.setText(Valor);
                Verificar();
                break;
            case 4:
                Abutton.setText(Valor);
                Verificar();
                break;
            case 5:
                Bbutton.setText(Valor);
                Verificar();
                break;
            case 6:
                Cbutton.setText(Valor);
                Verificar();
                break;
            default:
        }


    }

    //verifica y calcula los resultados
    public void Verificar() {
        a = 0; b = 0; c = 0; A = 0; B = 0; C = 0;
        String aText, bText, cText, AText, BText, CText;
        aText = abutton.getText().toString();
        bText = bbutton.getText().toString();
        cText = cbutton.getText().toString();
        AText = Abutton.getText().toString();
        BText = Bbutton.getText().toString();
        CText = Cbutton.getText().toString();

        try {
            if (!aText.equals("a")) {
                a = Double.parseDouble((String) abutton.getText());
            }
            if (!bText.equals("b")) {
                b = Double.parseDouble((String) bbutton.getText());
            }
            if (!cText.equals("c")) {
                c = Double.parseDouble((String) cbutton.getText());
            }
            if (!AText.equals("A")) {
                A = Double.parseDouble((String) Abutton.getText());
            }
            if (!BText.equals("B")) {
                B = Double.parseDouble((String) Bbutton.getText());
            }
            if (!CText.equals("C")) {
                C = Double.parseDouble((String) Cbutton.getText());
            }

            if (a > 0 || c > 0) {
                if (a > 0) {
                    c = 180 - 90 - a;

                    cbutton.setText(Double.toString(c));
                    //cbutton.setText(Double.toString(c));
                    cbutton.setEnabled(false);
                }
                if (c > 0) {
                    a = 180 - 90 - c;
                    abutton.setText(Double.toString(a));
                    abutton.setEnabled(false);
                }
            }


            //Resolucion de triangulo rectangulo

                if (a + b + c > 180 || a >= 90 || c >= 90) {
                    ShowToast(">180°");
                    abutton.setText("a");
                    bbutton.setText("b");
                    cbutton.setText("c");
                    abutton.setEnabled(true);
                    bbutton.setEnabled(true);
                    cbutton.setEnabled(true);
                }
                if (a > 0) {
                    if (A > 0 && OK == 0) {
                        C = A / cos(toRadians(a));
                        Cbutton.setText(Double.toString( C));
                        B = C * sin(toRadians(a));
                        Bbutton.setText(Double.toString( B));
                        Disable();

                    }
                    if (B > 0 && OK == 0) {

                        C = B / sin(toRadians(a));
                        Cbutton.setText(Double.toString(C));
                        A = C * cos(toRadians(a));
                        Abutton.setText(Double.toString(A));
                        Disable();
                    }
                    if (C > 0 && OK == 0) {

                        A = C * cos(toRadians(a));
                        Abutton.setText(Double.toString(A));
                        B = C * sin(toRadians(a));
                        Bbutton.setText(Double.toString(B));
                        Disable();

                    }
                }

                if (c > 0) {

                    if (A > 0 && OK == 0) {
                        C = A / cos(toRadians(c));
                        Cbutton.setText(Double.toString(C));
                        B = C * sin(toRadians(c));
                        Bbutton.setText(Double.toString(B));
                        Disable();
                    }
                    if (B > 0 && OK == 0) {

                        C = B / sin(toRadians(c));
                        Cbutton.setText(Double.toString(C));
                        A = C * cos(toRadians(a));
                        Abutton.setText(Double.toString(A));
                        Disable();
                    }

                    if (C > 0 && OK == 0) {
                        A = C * sin(toRadians(c));
                        Abutton.setText(Double.toString(A));
                        B = C * cos(toRadians(c));
                        Bbutton.setText(Double.toString(B));
                        Disable();
                    }
                }
                if (A > 0 && B > 0 && OK == 0) {
                    c = toDegrees(atan(A / B));
                    cbutton.setText(Double.toString(c));
                    a = 90 - c;
                    abutton.setText(Double.toString(a));
                    C = B / sin(toRadians(a));
                    Cbutton.setText(Double.toString(C));
                    Disable();
                }

                if (A > 0 && C > 0 && OK == 0) {
                    c = toDegrees(Math.asin(A / C));
                    cbutton.setText(Double.toString(c));
                    a = 90 - c;
                    abutton.setText(Double.toString(a));
                    B = C * cos(toRadians(c));
                    Bbutton.setText(Double.toString(B));
                    Disable();
                }
                if (B > 0 && C > 0 && OK == 0) {
                    a = toDegrees(Math.asin(B / C));
                    abutton.setText(Double.toString(a));
                    c = 90 - a;
                    cbutton.setText(Double.toString(c));
                    A = C * sin(toRadians(c));
                    Abutton.setText(Double.toString(A));
                    Disable();
                }





        } catch (Exception e) {
            String message = e.getMessage();
            ShowToast(message);
        }
        if (a > 0 && b > 0 && c > 0) {

            if (a == b && a == c && b == c) {
                TipoTriangulo.setText(R.string.Equilatero);
            } else {
                if (a != b && a != c && b != c) {
                    TipoTriangulo.setText(R.string.Escaleno);
                } else {
                    TipoTriangulo.setText(R.string.Isosceles);
                }
            }
        }

    }

    private void Disable() {
        OK = 1;
        abutton.setEnabled(false);
        bbutton.setEnabled(false);
        cbutton.setEnabled(false);
        Abutton.setEnabled(false);
        Bbutton.setEnabled(false);
        Cbutton.setEnabled(false);

        abutton.setTextColor(Color.BLACK);
        bbutton.setTextColor(Color.BLACK);
        cbutton.setTextColor(Color.BLACK);
        Abutton.setTextColor(Color.BLACK);
        Bbutton.setTextColor(Color.BLACK);
        Cbutton.setTextColor(Color.BLACK);

        abutton.setText(String.format( "%.2f", a ) );
        bbutton.setText(String.format( "%.2f", b ) );
        cbutton.setText(String.format( "%.2f", c ) );
        Abutton.setText(String.format( "%.2f", A ) );
        Bbutton.setText(String.format( "%.2f", B ) );
        Cbutton.setText(String.format( "%.2f", C ) );
    }

    private void Limpiar(){
        abutton.setText("a");
        bbutton.setText("b");
        cbutton.setText("c");
        abutton.setEnabled(true);
        bbutton.setEnabled(true);
        cbutton.setEnabled(true);
        Abutton.setText("A");
        Bbutton.setText("B");
        Cbutton.setText("C");
        Abutton.setEnabled(true);
        Bbutton.setEnabled(true);
        Cbutton.setEnabled(true);
        a = 0; b = 0; c = 0; A = 0; B = 0; C = 0;
        TipoTriangulo.setText(R.string.tipo);
    }
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.salir)
                .setMessage(R.string.botonsalir)
                .setPositiveButton(R.string.salir, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.quedar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }

                })
                .setNeutralButton(R.string.reset, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Limpiar();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    //muestra un toast mandando solo el string
    private void ShowToast(String mensaje) {
        Toast msg = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        msg.show();
    }
}


